package datamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Customer with ID, name and contacts.
 * ID can only be set once.
 * Duplicate contacts are ignored.
 * 
 * @author YOUR_NAME
 */
public class Customer {

    private long id = -1;
    private String lastName = "";
    private String firstName = "";
    private final List<String> contacts = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Customer() {
    }

    /**
     * Constructor that takes full name string.
     * @param name full name string
     */
    public Customer(String name) {
        splitName(name);
    }

    /**
     * Returns the customer ID.
     * @return customer ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the customer ID, only once.
     * @param id customer ID
     * @return this customer object
     */
    public Customer setId(long id) {
        if (this.id == -1) {
            this.id = id;
        }
        return this;
    }

    /**
     * Returns the last name.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the first name.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets both first and last name.
     * @param first first name
     * @param last last name
     * @return this customer object
     */
    public Customer setName(String first, String last) {
        this.firstName = first != null ? first.trim() : "";
        this.lastName = last != null ? last.trim() : "";
        return this;
    }

    /**
     * Sets full name, splitting it automatically.
     * @param name full name string
     * @return this customer object
     */
    public Customer setName(String name) {
        splitName(name);
        return this;
    }

    /**
     * Returns the number of contacts.
     * @return number of contacts
     */
    public int contactsCount() {
        return contacts.size();
    }

    /**
     * Returns the contact list.
     * @return contacts as Iterable
     */
    public Iterable<String> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    /**
     * Adds a contact, ignoring duplicates.
     * @param contact contact string
     * @return this customer object
     */
    public Customer addContact(String contact) {
        if (contact != null && !contacts.contains(contact)) {
            contacts.add(contact);
        }
        return this;
    }

    /**
     * Deletes the contact at index i, if valid.
     * @param i index of contact
     */
    public void deleteContact(int i) {
        if (i >= 0 && i < contacts.size()) {
            contacts.remove(i);
        }
    }

    /**
     * Deletes all contacts.
     */
    public void deleteAllContacts() {
        contacts.clear();
    }

    /**
     * Splits full name string into first and last name.
     * @param name full name
     */
    private void splitName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.firstName = "";
            this.lastName = "";
            return;
        }

        String[] parts;
        if (name.contains(",")) {
            parts = name.split(",", 2);
            this.lastName = parts[0].trim();
            this.firstName = parts[1].trim();
        } else {
            parts = name.trim().split("\\s+");
            if (parts.length == 1) {
                this.firstName = parts[0];
                this.lastName = "";
            } else {
                this.lastName = parts[parts.length - 1];
                this.firstName = String.join(" ", java.util.Arrays.copyOf(parts, parts.length - 1));
            }
        }
    }
}
