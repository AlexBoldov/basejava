import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int resumeCounter;

    void clear() {
        Arrays.fill(storage, 0, resumeCounter, null);
        resumeCounter = 0;
    }

    void save(Resume r) {
        storage[resumeCounter] = r;
        resumeCounter++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < resumeCounter; i++) {
            if (storage[i].uuid.equals(uuid) && resumeCounter + 1 - i >= 0) {
                System.arraycopy(storage, i + 1, storage, i, resumeCounter + 1 - i);
                resumeCounter--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        if (resumeCounter != 0) {
            return Arrays.copyOf(storage, resumeCounter);
        }
        return new Resume[0];
    }

    int size() {
        return resumeCounter;
    }
}
