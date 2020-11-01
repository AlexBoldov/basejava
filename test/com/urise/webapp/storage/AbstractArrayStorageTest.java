package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    Resume resume_1 = new Resume("uuid_1");
    Resume resume_2 = new Resume("uuid_2");
    Resume resume_3 = new Resume("uuid_3");
    Resume resume_4 = new Resume();

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(resume_1);
        Assert.assertSame(resume_1, storage.get(resume_1.getUuid()));
    }

    @Test (expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume());
    }

    @Test
    public void save() {
        int sizeBeforeSave = storage.size();
        storage.save(resume_4);
        Assert.assertEquals(sizeBeforeSave + 1, storage.size());
        Assert.assertEquals(resume_4, storage.get(resume_4.getUuid()));
    }

    @Test (expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume_1);
    }

    @Test
    public void get() {
        Assert.assertEquals(resume_3, storage.get(resume_3.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        int sizeBeforeDelete = storage.size();
        storage.delete(resume_2.getUuid());
        Assert.assertEquals(sizeBeforeDelete - 1, storage.size());
        storage.get(resume_2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(resumes.length, storage.size());
        Assert.assertEquals(resume_1, resumes[0]);
        Assert.assertEquals(resume_2, resumes[1]);
        Assert.assertEquals(resume_3, resumes[2]);
    }

    @Test
    public void size() {
            Assert.assertEquals(3, storage.size());
    }

    @Test (expected = StorageException.class)
    public void overflowStorage() {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        System.out.println(storage.size());
        storage.save(new Resume());
    }
}