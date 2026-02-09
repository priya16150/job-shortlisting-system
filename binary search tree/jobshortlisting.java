import java.util.Scanner;

// Applicant Node 
class Applicant {
    int experience;
    String name;
    Applicant left, right;

    Applicant(int experience, String name) {
        this.experience = experience;
        this.name = name;
        left = right = null;
    }
}

//Binary Search Tree 
class ApplicantBST {
    Applicant root;

    /* Insert Applicant */
    void insert(int exp, String name) {
        root = insertRec(root, exp, name);
    }

    private Applicant insertRec(Applicant root, int exp, String name) {
        if (root == null)
            return new Applicant(exp, name);

        if (exp < root.experience)
            root.left = insertRec(root.left, exp, name);
        else
            root.right = insertRec(root.right, exp, name);

        return root;
    }

    // Display Applicants (Inorder) 
    void display() {
        if (root == null) {
            System.out.println("No applicants found.");
            return;
        }
        System.out.println("\nApplicants (Sorted by Experience):");
        inorder(root);
    }

    private void inorder(Applicant root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.name + " - " + root.experience + " years");
            inorder(root.right);
        }
    }

    // Shortlist by Experience Range 
    void shortlist(int min, int max) {
        System.out.println("\nShortlisted Candidates:");
        shortlistRec(root, min, max);
    }

    private void shortlistRec(Applicant root, int min, int max) {
        if (root == null) return;

        if (root.experience > min)
            shortlistRec(root.left, min, max);

        if (root.experience >= min && root.experience <= max)
            System.out.println(root.name + " (" + root.experience + " years)");

        if (root.experience < max)
            shortlistRec(root.right, min, max);
    }

    // Find Best Candidate 
    void bestCandidate() {
        if (root == null) {
            System.out.println("No applicants available.");
            return;
        }

        Applicant current = root;
        while (current.right != null)
            current = current.right;

        System.out.println("Best Candidate: " +
                current.name + " (" + current.experience + " years)");
    }

    //Count Eligible Applicants 
    int countEligible(int minExp) {
        return countEligibleRec(root, minExp);
    }

    private int countEligibleRec(Applicant root, int minExp) {
        if (root == null) return 0;

        if (root.experience >= minExp)
            return 1 + countEligibleRec(root.left, minExp)
                     + countEligibleRec(root.right, minExp);
        else
            return countEligibleRec(root.right, minExp);
    }
} 
public class jobshortlisting {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ApplicantBST bst = new ApplicantBST();

        while (true) {
            System.out.println("\n--- Job Applicant Shortlisting System ---");
            System.out.println("1. Add Applicant");
            System.out.println("2. Display Applicants");
            System.out.println("3. Shortlist by Experience");
            System.out.println("4. Best Candidate");
            System.out.println("5. Count Eligible Applicants");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = in.next();
                    System.out.print("Enter Experience (years): ");
                    int exp = in.nextInt();
                    bst.insert(exp, name);
                    System.out.println("Applicant Added.");
                    break;

                case 2:
                    bst.display();
                    break;

                case 3:
                    System.out.print("Enter Min Experience: ");
                    int min = in.nextInt();
                    System.out.print("Enter Max Experience: ");
                    int max = in.nextInt();
                    bst.shortlist(min, max);
                    break;

                case 4:
                    bst.bestCandidate();
                    break;

                case 5:
                    System.out.print("Enter Required Experience: ");
                    int req = in.nextInt();
                    System.out.println("Eligible Applicants: " +
                            bst.countEligible(req));
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
