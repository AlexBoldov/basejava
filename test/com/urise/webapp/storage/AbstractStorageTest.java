package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected final Storage storage;

    Resume resume_1 = new Resume("uuid_1");
    Resume resume_2 = new Resume("uuid_2");
    Resume resume_3 = new Resume("uuid_3");
    Resume resume_4 = new Resume();

    AbstractStorageTest(Storage storage) {
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
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(resume_1);
        assertSame(resume_1, storage.get(resume_1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume_4);
    }

    @Test
    public void save() {
        int sizeBeforeSave = storage.size();
        storage.save(resume_4);
        assertEquals(sizeBeforeSave + 1, storage.size());
        assertThat(resume_4, is(storage.get(resume_4.getUuid())));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume_1);
    }

    @Test
    public void get() {
        assertThat(resume_3, is(storage.get(resume_3.getUuid())));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        int sizeBeforeDelete = storage.size();
        storage.delete(resume_2.getUuid());
        assertEquals(sizeBeforeDelete - 1, storage.size());
        storage.get(resume_2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] expectedResumes = storage.getAll();
        Resume[] actualResumes = {resume_1, resume_2, resume_3};
        assertArrayEquals(expectedResumes, actualResumes);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}
