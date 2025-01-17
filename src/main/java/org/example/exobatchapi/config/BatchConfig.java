package org.example.exobatchapi.config;

import org.example.exobatchapi.tasklet.ExtractingAlbumsFromCSVTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
    private final ExtractingAlbumsFromCSVTasklet extractingAlbumsFromCSVTasklet;

    public BatchConfig(ExtractingAlbumsFromCSVTasklet extractingAlbumsFromCSVTasklet) {
        this.extractingAlbumsFromCSVTasklet = extractingAlbumsFromCSVTasklet;
    }

    @Bean
    public Job importJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("extractDataJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("extractDataStep", jobRepository)
                .tasklet(extractingAlbumsFromCSVTasklet, transactionManager)
                .build();
    }
}
