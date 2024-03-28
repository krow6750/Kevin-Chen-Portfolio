/**
 * Name: Kevin Chen
 * Assignment: Lab 5
 * Date: 04/03/2023
 * Notes: This class represents an internal employee who has no managerial responsibilities.
 */


/**
 * This class represents an internal employee who has no managerial responsibilities.
 */
public class InternalEmployee extends NonManagerEmployee {
    public InternalEmployee(String name, double pay, Gender gender) {
        super(name, pay, gender);
    }
}