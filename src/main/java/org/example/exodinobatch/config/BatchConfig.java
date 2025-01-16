package org.example.exodinobatch.config;

import org.example.exodinobatch.model.Dino;
import org.example.exodinobatch.entity.ParsedDino;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
//@EnableBatchProcessing
public class BatchConfig {

    public BatchConfig() {}

    @Bean
    public FlatFileItemReader<Dino> reader() {
        return new FlatFileItemReaderBuilder<Dino>()
                .name("dinoReader")
                .resource(new ClassPathResource("dinosaurs.csv"))
                .linesToSkip(1)
                .delimited()
                .names("id","name","species","age_million_years")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Dino.class);
                }})
                .build();
    }

    @Bean
    public ItemProcessor<Dino, ParsedDino> processor() {
        return dino -> new ParsedDino(
                dino.getId(),
                dino.getName(),
                dino.getSpecies(),
                dino.getAgeInMillionsYears() * 10_000);
    }

    @Bean
    public JdbcBatchItemWriter<ParsedDino> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ParsedDino>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO parsed_dino (id, name, species, age_in_centuries) VALUES (:id, :name, :species, :ageInCenturies)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("importDinoJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(
            ItemReader<Dino> reader,
            ItemProcessor<Dino,ParsedDino> processor,
            ItemWriter<ParsedDino> writer,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("step",jobRepository)
                .<Dino, ParsedDino>chunk(200, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
