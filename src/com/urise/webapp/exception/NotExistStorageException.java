package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Operation fail. Resume " + uuid + " not found", uuid);
    }
}
