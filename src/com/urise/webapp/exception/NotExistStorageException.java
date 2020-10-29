package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("ERROR: Operation fail. Resume " + uuid + " not found", uuid);
    }
}
