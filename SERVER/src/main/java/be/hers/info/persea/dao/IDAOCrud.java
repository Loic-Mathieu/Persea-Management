package be.hers.info.persea.dao;

import java.util.List;

interface IDAOCrud<T> {

    /**
     * Adds one element in the table T
     * @param newElement element to
     */
    public void addOne(T newElement);

    /**
     * Adds a list of elements in the table T
     * @param newElements elements to add
     */
    public void addAll(List<T> newElements);

    /**
     * Get an element in the table T by its id
     * @param id id of the target element
     * @return the element from the table T with the targeted id
     */
    public T getById(long id);

    /**
     * Get all elements of the table T
     * @return a list of all T elements
     */
    public List<T> getAll();

    /**
     * Delete the element in table T with targeted id
     * @param id id of the target element
     */
    public void remove(long id);

    /**
     * Update an element in the table T with targeted id
     * @param id id of the target element
     * @param newElement element to update
     */
    public void put(long id, T newElement);
}
