package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("ERROR: Operation fail. Resume " + uuid + " already exists", uuid);
    }
}
