import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import java.util.LinkedList;

public class ShortestPathsTest {


    /* Returns the Graph loaded from the file with filename fn. */
    private Graph loadBasicGraph(String fn) {
        Graph result = null;
        try {
            result = ShortestPaths.parseGraph("basic", fn);
        } catch (FileNotFoundException e) {
            fail("Could not find graph " + fn);
        }
        return result;
    }
    private void assertPath(ShortestPaths sp, Node a, Node b, int size, double length) {
        LinkedList<Node> path = sp.shortestPath(b);
        if (path == null) {
            assertEquals(Double.POSITIVE_INFINITY, sp.shortestPathLength(b), 1e-6);
            return;
        }
        assertEquals(size, path.size());
        assertEquals(a, path.getFirst());
        assertEquals(b, path.getLast());
        assertEquals(length, sp.shortestPathLength(b), 1e-6);
    }

    /** Dummy test case demonstrating syntax to create a graph from scratch.
     * TODO Write your own tests below. */
    @Before

    /** Minimal test case to check the path from A to B in Simple0.txt */
    @Test //Testing compute is working properly
    public void test01Simple0() {
        Graph g = loadBasicGraph("data/Simple0.txt");
        g.report();
        Node a = g.getNode("A");
        Node b = g.getNode("B");
        Node c = g.getNode("C");
        ShortestPaths asp = new ShortestPaths();
        ShortestPaths bsp = new ShortestPaths();
        ShortestPaths csp = new ShortestPaths();
        asp.compute(a);
        bsp.compute(b);
        csp.compute(c);
        assertPath(asp, a, b, 2, 1.0);
        assertPath(asp, a, c, 2, 2.0);
        assertPath(csp, c, b, 2, 2.0);
    }
    @Test //Testing compute to see if it understands there is no path to destination
    public void test02Simple0NoPath() {
        Graph g = loadBasicGraph("data/Simple0.txt");
        g.report();
        Node a = g.getNode("A");
        Node b = g.getNode("B");
        Node c = g.getNode("C");
        ShortestPaths bsp = new ShortestPaths();
        ShortestPaths csp = new ShortestPaths();
        bsp.compute(b);
        csp.compute(c);
        assertPath(bsp,b,a,0,0);
        assertPath(bsp,b,c,0,0);
        assertPath(csp,c,a,0,0);
    }
    @Test
    public void test01Simple1() {
        Graph g = loadBasicGraph("data/Simple1.txt");
        g.report();
        Node a = g.getNode("A");
        Node b = g.getNode("B");
        Node c = g.getNode("C");
        Node d = g.getNode("D");
        Node s = g.getNode("S");
        ShortestPaths asp = new ShortestPaths();
        ShortestPaths bsp = new ShortestPaths();
        ShortestPaths csp = new ShortestPaths();
        ShortestPaths dsp = new ShortestPaths();
        ShortestPaths ssp = new ShortestPaths();
        asp.compute(a);
        bsp.compute(b);
        csp.compute(c);
        dsp.compute(d);
        ssp.compute(s);
        assertPath(ssp, s, a, 3, 8.0);
        assertPath(ssp, s, b, 4, 9.0);
        assertPath(ssp, s, c, 2, 5.0);
        assertPath(ssp, s, d, 3, 7.0);
        assertPath(asp, a, b, 2, 1.0);
        assertPath(asp, a, c, 2, 2.0);
        assertPath(asp, a, d, 3, 4.0);
        assertPath(asp, a, s, 4, 5.0);
        assertPath(bsp, b, a, 5, 13.0);
        assertPath(bsp, b, c, 4, 10.0);
        assertPath(bsp, b, d, 2, 4.0);
        assertPath(bsp, b, s, 3, 5.0);
        assertPath(csp, c, a, 2, 3.0);
        assertPath(csp, c, b, 3, 4.0);
        assertPath(csp, c, d, 2, 2.0);
        assertPath(csp, c, s, 3, 3.0);
        assertPath(dsp, d, a, 4, 9.0);
        assertPath(dsp, d, b, 2, 7.0);
        assertPath(dsp, d, c, 3, 6.0);
        assertPath(dsp, d, s, 2, 1.0);
    }
    @Test
    public void test01Simple2() {
        Graph gr = loadBasicGraph("data/Simple2.txt");
        gr.report();
        Node a = gr.getNode("A");
        Node b = gr.getNode("B");
        Node c = gr.getNode("C");
        Node d = gr.getNode("D");
        Node e = gr.getNode("E");
        Node f = gr.getNode("F");
        Node g = gr.getNode("G");
        Node h = gr.getNode("H");
        Node i = gr.getNode("I");
        Node j = gr.getNode("J");
        ShortestPaths asp = new ShortestPaths();
        ShortestPaths bsp = new ShortestPaths();
        ShortestPaths csp = new ShortestPaths();
        ShortestPaths dsp = new ShortestPaths();
        ShortestPaths esp = new ShortestPaths();
        ShortestPaths fsp = new ShortestPaths();
        ShortestPaths gsp = new ShortestPaths();
        ShortestPaths hsp = new ShortestPaths();
        ShortestPaths isp = new ShortestPaths();
        ShortestPaths jsp = new ShortestPaths();
        asp.compute(a);
        bsp.compute(b);
        csp.compute(c);
        dsp.compute(d);
        esp.compute(e);
        fsp.compute(f);
        gsp.compute(g);
        hsp.compute(h);
        isp.compute(i);
        jsp.compute(j);
        assertPath(asp, a, b, 4, 5.0);
        assertPath(asp, a, c, 4, 7.0);
        assertPath(asp, a, e, 2, 1.0);
        assertPath(asp, a, f, 3, 4.0);
        assertPath(asp, a, g, 6, 8.0);
        assertPath(asp, a, h, 2, 10.0);
        assertPath(asp, a, i, 4, 5.0);
        assertPath(asp, a, j, 5, 7.0);
        assertPath(bsp, b, c, 2, 2.0);
        assertPath(dsp, d, a, 2, 4.0);
        assertPath(dsp, d, b, 5, 9.0);
        assertPath(dsp, d, c, 5, 11.0);
        assertPath(dsp, d, e, 3, 5.0);
        assertPath(dsp, d, f, 4, 8.0);
        assertPath(dsp, d, g, 7, 12.0);
        assertPath(dsp, d, h, 2, 1.0);
        assertPath(dsp, d, i, 5, 9.0);
        assertPath(dsp, d, j, 6, 11.0);
        assertPath(esp, e, b, 3, 4.0);
        assertPath(esp, e, c, 3, 6.0);
        assertPath(esp, e, f, 2, 3.0);
        assertPath(esp, e, g, 5, 7.0);
        assertPath(esp, e, i, 3, 4.0);
        assertPath(esp, e, j, 4, 6.0);
        assertPath(fsp, f, b, 2, 1.0);
        assertPath(fsp, f, c, 2, 3.0);
        assertPath(fsp, f, g, 4, 4.0);
        assertPath(fsp, f, i, 2, 1.0);
        assertPath(fsp, f, j, 3, 3.0);
        assertPath(hsp, h, b, 4, 9.0);
        assertPath(hsp, h, c, 4, 11.0);
        assertPath(hsp, h, e, 2, 5.0);
        assertPath(hsp, h, f, 3, 8.0);
        assertPath(hsp, h, g, 4, 12.0);
        assertPath(hsp, h, i, 2, 9.0);
        assertPath(hsp, h, j, 3, 11.0);
        assertPath(isp, i, g, 3, 3.0);
        assertPath(isp, i, j, 2, 2.0);
        assertPath(jsp, j, g, 2, 1.0);
    }
    @Test
    public void test02Simple2NoPath() {
        Graph gr = loadBasicGraph("data/Simple2.txt");
        gr.report();
        Node a = gr.getNode("A");
        Node b = gr.getNode("B");
        Node c = gr.getNode("C");
        Node d = gr.getNode("D");
        Node e = gr.getNode("E");
        Node f = gr.getNode("F");
        Node g = gr.getNode("G");
        Node h = gr.getNode("H");
        Node i = gr.getNode("I");
        Node j = gr.getNode("J");
        ShortestPaths asp = new ShortestPaths();
        ShortestPaths bsp = new ShortestPaths();
        ShortestPaths csp = new ShortestPaths();
        ShortestPaths dsp = new ShortestPaths();
        ShortestPaths esp = new ShortestPaths();
        ShortestPaths fsp = new ShortestPaths();
        ShortestPaths gsp = new ShortestPaths();
        ShortestPaths hsp = new ShortestPaths();
        ShortestPaths isp = new ShortestPaths();
        ShortestPaths jsp = new ShortestPaths();
        asp.compute(a);
        bsp.compute(b);
        csp.compute(c);
        dsp.compute(d);
        esp.compute(e);
        fsp.compute(f);
        gsp.compute(g);
        hsp.compute(h);
        isp.compute(i);
        jsp.compute(j);
        assertPath(bsp, b, d, 0, 0);
        assertPath(bsp, b, e, 0, 0);
        assertPath(bsp, b, f, 0, 0);
        assertPath(bsp, b, g, 0, 0);
        assertPath(bsp, b, h, 0, 0);
        assertPath(bsp, b, i, 0, 0);
        assertPath(bsp, b, j, 0, 0);
        assertPath(csp, c, a, 0, 0);
        assertPath(csp, c, b, 0, 0);
        assertPath(csp, c, d, 0, 0);
        assertPath(csp, c, e, 0, 0);
        assertPath(csp, c, f, 0, 0);
        assertPath(csp, c, g, 0, 0);
        assertPath(csp, c, h, 0, 0);
        assertPath(csp, c, i, 0, 0);
        assertPath(csp, c, j, 0, 0);
        assertPath(esp, e, a, 0, 0);
        assertPath(esp, e, d, 0, 0);
        assertPath(esp, e, h, 0, 0);
        assertPath(fsp, f, a, 0, 0);
        assertPath(fsp, f, d, 0, 0);
        assertPath(fsp, f, e, 0, 0);
        assertPath(fsp, f, h, 0, 0);
        assertPath(gsp, g, a, 0, 0);
        assertPath(gsp, g, b, 0, 0);
        assertPath(gsp, g, c, 0, 0);
        assertPath(gsp, g, d, 0, 0);
        assertPath(gsp, g, e, 0, 0);
        assertPath(gsp, g, f, 0, 0);
        assertPath(gsp, g, h, 0, 0);
        assertPath(gsp, g, i, 0, 0);
        assertPath(gsp, g, j, 0, 0);
        assertPath(hsp, h, a, 0, 0);
        assertPath(hsp, h, d, 0, 0);
        assertPath(isp, i, a, 0, 0);
        assertPath(isp, i, b, 0, 0);
        assertPath(isp, i, c, 0, 0);
        assertPath(isp, i, d, 0, 0);
        assertPath(isp, i, e, 0, 0);
        assertPath(isp, i, f, 0, 0);
        assertPath(isp, i, h, 0, 0);
        assertPath(jsp, j, a, 0, 0);
        assertPath(jsp, j, b, 0, 0);
        assertPath(jsp, j, c, 0, 0);
        assertPath(jsp, j, d, 0, 0);
        assertPath(jsp, j, e, 0, 0);
        assertPath(jsp, j, f, 0, 0);
        assertPath(jsp, j, h, 0, 0);
        assertPath(jsp, j, i, 0, 0);
    }
    @Test //Testing origin to itself
    public void test01SimpleItself() {
        Graph gr = loadBasicGraph("data/Simple0.txt");
        gr.report();
        Node a = gr.getNode("A");
        ShortestPaths sp = new ShortestPaths();
        sp.compute(a);
        assertPath(sp, a, a, 1, 0.0);
    }
    /* Pro tip: unless you include @Test on the line above your method header,
     * JUnit will not run it! This gets me every time. */
}
