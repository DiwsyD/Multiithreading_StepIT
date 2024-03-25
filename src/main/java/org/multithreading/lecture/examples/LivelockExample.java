package org.multithreading.lecture.examples;

public class LivelockExample {

    static class Spoon {
        private Person owner;

        public Spoon(Person d) {
            owner = d;
        }

        public synchronized void use() {
            System.out.printf("%s has the spoon.%n", owner.getName());
        }

        public synchronized void setOwner(Person d) {
            owner = d;
        }
    }

    static class Person {
        private String name;
        private boolean isHungry;

        public Person(String name) {
            this.name = name;
            isHungry = true;
        }

        public String getName() {
            return name;
        }

        public void eatWith(Spoon spoon, Person spouse) {
            while (isHungry) {
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1); // Simulate thinking
                    } catch (InterruptedException e) {
                        continue;
                    }
                    continue;
                }

                // Check if the spouse is hungry, and if so, yield the spoon to them
                if (spouse.isHungry) {
                    System.out.printf("%s gives the spoon to %s.%n", name, spouse.getName());
                    spoon.setOwner(spouse);
                    continue;
                }

                // Eat with the spoon
                spoon.use();
                isHungry = false;
                System.out.printf("%s is full!%n", name);

                // Yield the spoon to the spouse
                System.out.printf("%s gives the spoon to %s.%n", name, spouse.getName());
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {
        Spoon spoon = new Spoon(new Person("Alice"));
        Person husband = new Person("Bob");

        // Start threads for both diners
        Thread aliceThread = new Thread(() -> spoon.owner.eatWith(spoon, husband));
        Thread bobThread = new Thread(() -> husband.eatWith(spoon, spoon.owner));

        aliceThread.start();
        bobThread.start();
    }
}