package resume.coding.dsalgo3;

import org.junit.Assert;
import org.junit.Test;

public class ListTest {

    @Test
    public void listInsertTest() {
        ListDef<Integer> list = new ListDef<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        var p = list.head;

        for (int i = 3; p != null; i--) {
            Assert.assertEquals(i, p.data.intValue());
            p = p.next;
        }
    }

    @Test
    public void listFindTest() {
        var l = new ListDef<>();
        l.insert("Java");
        l.insert("C++");
        l.insert("Python");
        l.insert("Haskell");

        Assert.assertEquals("C++", l.find(x -> x.equals("C++")).data);
        Assert.assertNull(l.find(x -> x.equals("Ruby")));

        l.remove(l.find(x -> x.equals("Java")));
        Assert.assertNull(l.find(x -> x.equals("Java")));
    }

    @Test
    public void reverseTest() {
        ListDef<Integer> list = new ListDef<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.reverse1();
        var p = list.head;

        for (int i = 1; p != null; i++) {
            Assert.assertEquals(i, p.data.intValue());
            p = p.next;
        }

        list.reverse1();
        p = list.head;

        for (int i = 3; p != null; i--) {
            Assert.assertEquals(i, p.data.intValue());
            p = p.next;
        }
    }

    @Test
    public void reverseRecursiveTest() {
        ListDef<Integer> list = new ListDef<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        list.reverse2();
        var p = list.head;

        for (int i = 1; p != null; i++) {
            Assert.assertEquals(i, p.data.intValue());
            p = p.next;
        }

        list.reverse2();
        p = list.head;

        for (int i = 3; p != null; i--) {
            Assert.assertEquals(i, p.data.intValue());
            p = p.next;
        }
    }

    @Test
    public void hasLoopTest() {
        ListDef<Integer> list = new ListDef<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.find(x -> x == 3).next = list.head;

        Assert.assertTrue(list.hasLoop());
    }
}
