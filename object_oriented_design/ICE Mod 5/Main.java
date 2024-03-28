

public class Main
{
    public static void main(String [] args)
    {
        //Create some employees to play with
        Employee m1 = new NonManagerEmployee("Bob the undfeated", 300.00, Gender.Male);
        Employee m2 = new NonManagerEmployee("Gorg the smelly", 350.00, Gender.Female);
        Employee m3 = new NonManagerEmployee("Nix the ugly", 50.00, Gender.Male);
        Employee m4 = new NonManagerEmployee("Fredick the killer", 550.00, Gender.Male);
        Employee m5 = new NonManagerEmployee("Sue", 1000.00, Gender.Female);
        Employee m6 = new NonManagerEmployee("Hydra the sneaky", 300.00, Gender.Male);
        Employee m7 = new NonManagerEmployee("Gin the drunk", 300.00, Gender.Male);
        Employee m8 = new NonManagerEmployee("Lin the determined", 300.00, Gender.Female);
        Employee m9 = new ContractEmployee("Blarg the big", 300.00, Gender.Female,31,10,2020);

        //Generate the organization
        OrganizationImpl monsterCorp = new OrganizationImpl("Ug the Terrible", 300000.00,Gender.UnDisclosed);
        monsterCorp.addEmployee(m1, "Ug the Terrible");
        monsterCorp.addEmployee(m2, "Ug the Terrible");
        monsterCorp.addEmployee(m3,m1.getName());
        monsterCorp.addEmployee(m4,m2.getName());
        monsterCorp.addEmployee(m5,m2.getName());
        monsterCorp.addEmployee(m6,m4.getName());
        monsterCorp.addEmployee(m7,m4.getName());
        monsterCorp.addEmployee(m8,m5.getName());
        monsterCorp.addEmployee(m9,m5.getName());
        System.out.println(monsterCorp);
        System.out.println();
        System.out.println("Employees count with 300 salary : " + monsterCorp.getSize(employee -> employee.getAnnualPay() == 300));
        System.out.println("Employees with name == Lin : " + monsterCorp.allEmployees(employee -> employee.getName().contains("Lin")));
        System.out.println(monsterCorp.allEmployees(m->!(m.getName().contains("the"))));
		/*
		//test the print
		System.out.println(m1,"Ug the Terrible");
		System.out.println(monsterCorp);
		*/

		/*
		//add a single employee test
		monsterCorp.addEmployee(m1,"Ug the Terrible");
		System.out.println(monsterCorp);
		System.out.println();
		*/

		/*
		//Create a hierarchy and test
		monsterCorp.addEmployee(m2, "Ug the Terrible");
		monsterCorp.addEmployee(m3,m1.getName());
		monsterCorp.addEmployee(m4,m2.getName());
		monsterCorp.addEmployee(m5,m2.getName());
		monsterCorp.addEmployee(m6,m4.getName());
		monsterCorp.addEmployee(m7,m4.getName());
		monsterCorp.addEmployee(m8,m5.getName());
		monsterCorp.addEmployee(m9,m5.getName());
		System.out.println(monsterCorp);
		*/

        //System.out.println(monsterCorp.getSize());

        //System.out.println(monsterCorp.getSize(m->m.getAnnualPay()==300.00));

        //System.out.println(monsterCorp.allEmployees());

        //System.out.println(monsterCorp.allEmployees(m->!(m.getName().contains("the"))));

		/*
		System.out.println("m1 Supervisor : " + monsterCorp.getSupervisor(m1).getName());
		System.out.println("m2 Supervisor : " + monsterCorp.getSupervisor(m2).getName());
		System.out.println("m3 Supervisor : " + monsterCorp.getSupervisor(m3).getName());
		System.out.println("m4 Supervisor : " + monsterCorp.getSupervisor(m4).getName());
		System.out.println("m5 Supervisor : " + monsterCorp.getSupervisor(m5).getName());
		System.out.println("m6 Supervisor : " + monsterCorp.getSupervisor(m6).getName());
		System.out.println("m7 Supervisor : " + monsterCorp.getSupervisor(m7).getName());
		System.out.println("m8 Supervisor : " + monsterCorp.getSupervisor(m8).getName());
		System.out.println("m9 Supervisor : " + monsterCorp.getSupervisor(m9).getName());
		*/


		/*
		System.out.println("Before : \n" + monsterCorp);

		MonsterCorp.removeEmployee(m5,monsterCorp.getSupervisor(m5).getName());

		System.out.println("After : \n" + monsterCorp);

		System.out.println("m8 : " + monsterCorp.getSupervisor(m8).getName());
		*/
    }
}