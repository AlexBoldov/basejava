package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: Operation fail. No storage space");
        } else if (getIndexByUuid(resume.getUuid()) == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("ERROR: Operation fail. Resume " + resume.getUuid() + " already exists");
        }
    }

    public void delete(String uuid) {
        int resumeIndex = getIndexByUuid(uuid);
        if (resumeIndex != -1) {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Operation fail. Resume " + uuid + " not found");
        }
    }

    protected int getIndexByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) return i;
        }
        return -1;
    }
}
