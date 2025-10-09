/*
 * Course: SWE2410-121
 * Fall 2025-2026
 * File header contains class Subject
 * Name: aubryg
 * Created 10/9/2025
 */
package coconuts;

/**
 * Course SWE2410-121
 * Fall 2025-2026
 * Interface Subject Purpose: Interface for the subject
 *
 * @author aubryg
 * @version created on 10/9/2025 11:48 AM
 */
public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}