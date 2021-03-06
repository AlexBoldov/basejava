package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveToStorage(Resume resume, int searchKey) {
        int index = Math.abs(searchKey) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    protected void deleteFromStorage(int searchKey) {
        System.arraycopy(storage, searchKey + 1, storage, searchKey, size - 1 - searchKey);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "user");
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
