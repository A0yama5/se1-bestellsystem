package datamodel;

import java.util.*;

/**
 * Represents a customer with ID, name, and contacts.
 */
public class Customer {

        /**
     * Daten-Konstruktor, genutzt von DataFactory.
     * @param id eindeutige Kundennummer
     * @param lastName Nachname
     * @param firstName Vorname
     * @param contacts vorvalidierte Kontakte
     */
    public Customer(Long id, String lastName, String firstName, List<String> contacts) {
        this(firstName, lastName);   // ruft setName()
        setId(id);
        contacts.forEach(this::addContact);
    }


    private Long id;               // now defaults to null
    private String firstName;
    private String lastName;
    private final List<String> contacts;

    // Constructors
    public Customer() {
        // id stays null
        this.firstName = "";
        this.lastName = "";
        this.contacts = new ArrayList<>();
    }

    public Customer(String name) {
        this();
        setName(name);
    }

    public Customer(String firstName, String lastName) {
        this();
        setName(firstName, lastName);
    }

    // ID handling — chainable, only sets when id was previously null
    public Customer setId(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("invalid id (negative)");
        }
        if (this.id == null) {
            this.id = id;
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    // Name handling — both overloads chainable
    public Customer setName(String name) {
        splitName(name);
        return this;
    }

    public Customer setName(String firstName, String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("last name empty");
        }
        this.firstName = (firstName != null) ? firstName.trim() : "";
        this.lastName  = lastName.trim();
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Contacts — normalize then check length
    public Customer addContact(String contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact null");
        }
        String original  = contact;
        String trimmed   = original.trim();
        // strip quotes, semicolons, commas, then trim again
        String normalized = trimmed.replaceAll("[\"';,]", "").trim();

        if (normalized.length() < 6) {
            throw new IllegalArgumentException(
                "contact less than 6 characters: \"" + original + "\"."
            );
        }

        if (!contacts.contains(normalized)) {
            contacts.add(normalized);
        }
        return this;
    }

    public int contactsCount() {
        return contacts.size();
    }

    public Iterable<String> getContacts() {
        return contacts;
    }

    public void deleteContact(int i) {
        if (i >= 0 && i < contacts.size()) {
            contacts.remove(i);
        }
    }

    public void deleteAllContacts() {
        contacts.clear();
    }

    // Internal helper
    private void splitName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name null");
        }
        name = name.trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name empty");
        }

        name = name.replace(";", ",");  // normalize
        if (name.contains(",")) {
            String[] parts = name.split(",", 2);
            lastName  = parts[0].trim();
            firstName = parts[1].trim();
        } else {
            String[] parts = name.split("\\s+");
            if (parts.length == 1) {
                firstName = "";
                lastName  = parts[0].trim();
            } else {
                lastName  = parts[parts.length - 1].trim();
                firstName = String.join(" ",
                              Arrays.copyOf(parts, parts.length - 1)
                            ).trim();
            }
        }

        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("last name empty");
        }
    }
}
