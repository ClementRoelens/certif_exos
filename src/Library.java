import model.BorrowRecord;
import model.LibraryItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<LibraryItem> items;
    private final List<BorrowRecord> borrowRecords;


    public Library() {
        items = new ArrayList<LibraryItem>();
        borrowRecords = new ArrayList<>();
    }


    public List<LibraryItem> getItems() {
        return items;
    }

    public void borrowItem(LibraryItem item, String borrowerName) {
        borrowRecords.add(new BorrowRecord(item.getId(), borrowerName ,LocalDate.now().toString()));
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public List<LibraryItem> getAvailableItems() {
        List<Integer> borrowedItemIds = borrowRecords.stream().map(BorrowRecord::itemId).toList();

        return items.stream()
                .filter(b -> !borrowedItemIds.contains(b.getId()))
                .toList();
    }

    public List<LibraryItem> getBorrowedItems() {
        List<Integer> borrowedItemIds = borrowRecords.stream().map(BorrowRecord::itemId).toList();

        return items.stream()
                .filter(b -> borrowedItemIds.contains(b.getId()))
                .toList();
    }
}
