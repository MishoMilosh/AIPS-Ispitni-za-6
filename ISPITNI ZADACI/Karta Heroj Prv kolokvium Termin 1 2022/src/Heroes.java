import java.util.Scanner;

public class Heroes
{

    public static void startHeroesGame(SLL<Card> firstFriendCards, SLL<Card> secondFriendCards)
    {
        SLLNode<Card> najakakartaprv = firstFriendCards.getFirst();
        SLLNode<Card> pom1 = null;
        int max = 0;
        while(najakakartaprv!=null){
            int najakakarta = 0;
            najakakarta = najakakartaprv.element.getPower() * najakakartaprv.element.getNumAttacks();
            if(najakakarta>max){
                max = najakakarta;
                pom1 = najakakartaprv;
            }
            najakakartaprv = najakakartaprv.succ;
        }
        SLLNode<Card> vtor = secondFriendCards.getFirst();
        SLLNode<Card> pom2 = null;
        int counter = 0;
        while(vtor!=null){
            if(counter==2){
                pom2 = vtor;
                break;
            }
            vtor = vtor.succ;
            counter++;
        }
        firstFriendCards.delete(pom1);
        secondFriendCards.insertAfter(pom1.element,pom2);

    }
    public static void main(String[] args)
    {
        SLL<Card> prv = new SLL<>();
        SLL<Card> vtor = new SLL<>();
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<6;i++)
        {
            int id = scanner.nextInt();
            int power = scanner.nextInt();
            int numAttacks = scanner.nextInt();
            Card c1 = new Card(id,power,numAttacks);
            prv.insertLast(c1);
        }
        for(int j=0;j<6;j++)
        {
            int id = scanner.nextInt();
            int power = scanner.nextInt();
            int numAttacks = scanner.nextInt();
            Card c1 = new Card(id,power,numAttacks);
            vtor.insertLast(c1);
        }
        startHeroesGame(prv,vtor);
        System.out.println(prv.toString());
        System.out.println(vtor.toString());

    }
}
class Card
{
    int id;
    int power;
    int numAttacks;

    public Card(int id, int power, int numAttacks)
    {
        this.id = id;
        this.power = power;
        this.numAttacks = numAttacks;
    }

    public int getId() {
        return id;
    }

    public int getPower() {
        return power;
    }

    public int getNumAttacks() {
        return numAttacks;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + " ";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + " ";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first ==node){
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }
}