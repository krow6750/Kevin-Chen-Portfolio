/**
 * Name: Kevin Chen
 * Assignment: Lab 5
 * Date: 04/03/2023
 * Notes: Main driver for Organization
 */

/*
This is your starting point and your testing file. 
Except for task 3 the code should work without modifications once you have everything else implemented.
*/
class OrganizationDriver {
    public static void main(String[] args) {
        //Create some employees to play with
        NonManagerEmployee m1 = new NonManagerEmployee("Bob the undfeated", 300.00, Gender.Male);
        NonManagerEmployee m2 = new NonManagerEmployee("Gorg the smelly", 350.00, Gender.Female);
        NonManagerEmployee m3 = new NonManagerEmployee("Nix the ugly", 50.00, Gender.Male);
        NonManagerEmployee m4 = new NonManagerEmployee("Fredick the killer", 550.00, Gender.Male);
        NonManagerEmployee m5 = new NonManagerEmployee("Sue", 1000.00, Gender.Female);
        NonManagerEmployee m6 = new NonManagerEmployee("Hydra the sneaky", 300.00, Gender.Male);
        NonManagerEmployee m7 = new NonManagerEmployee("Gin the drunk", 300.00, Gender.Male);
        NonManagerEmployee m8 = new NonManagerEmployee("Lin the determined", 300.00, Gender.Female);
        ContractEmployee m9 = new ContractEmployee("Blarg the big", 300.00, Gender.Female, 31, 10, 2020);

        //Generate the organization
        OrganizationImpl MonsterCorp = new OrganizationImpl("Ug the Terrible", 300000.00, Gender.UnDisclosed);


        //Create a hierarchy and test
        MonsterCorp.addEmployee(m2, "Ug the Terrible");
        MonsterCorp.addEmployee(m1, m2.getName()); // Needed otherwise m1 isn't part of the corp
        MonsterCorp.addEmployee(m3, m1.getName());
        MonsterCorp.addEmployee(m4, m2.getName());
        MonsterCorp.addEmployee(m5, m2.getName());
        MonsterCorp.addEmployee(m6, m4.getName());
        MonsterCorp.addEmployee(m7, m4.getName());
        MonsterCorp.addEmployee(m8, m5.getName());
        MonsterCorp.addEmployee(m9, m5.getName());

        //This was part of the sample code provided my the module
        System.out.println(MonsterCorp.getSize());

        //Task 1:
        //This you'll have to add. We did it as part of the demo code, but you'll have to modify it to make it work with the new structure
        //Print all the employees along with all their information
        MonsterCorp.printEmployees();

        //Task 2:
        //This too is something we did in the demo code, but you'll have to modify it to work here.
        //The syntax on this is a bit challenging, but if you study the getSizeByGender you should be able to figure it out
        //Remember, don't try to guess. Try and understand the existing code and then see how you can change it
        //This code segment should return a single int value: the number of employees who make 300.00 annually
        System.out.println(MonsterCorp.count(m -> m.getAnnualPay() == 300.00));

        //Task 3:
        //Print out a list of just employee names using allEmployees()
        //This is not a hard task. Just make sure you understand what all Employees returns
        //This function should return a string of all employee names that you can then print as output
        System.out.println(MonsterCorp.allEmployees().toString());


        //Extra Task
        //Because we aren't implementing a filter, I'm removing this, but I invite you to find a workaround
        System.out.println(MonsterCorp.allEmployees(m -> !(m.getName().contains("the"))));

        //Extra Task
        //Consider this new structure. Go back and remove any extra code from this implementation and diagram the new structure
        //to confirm you know how it works.

        //Extra Task
        //Implement some new functionality on your own or add back some of the functionality that we had to remove for this new structure.
        System.out.println(MonsterCorp.countPayAbove(300));

        System.out.println(MonsterCorp.terminatedBefore(1, 11, 2020));
    }

}