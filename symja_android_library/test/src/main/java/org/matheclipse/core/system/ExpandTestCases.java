package org.matheclipse.core.system;

import static org.matheclipse.core.expression.F.C1;
import static org.matheclipse.core.expression.F.C1D2;
import static org.matheclipse.core.expression.F.C2;
import static org.matheclipse.core.expression.F.C3;
import static org.matheclipse.core.expression.F.Plus;
import static org.matheclipse.core.expression.F.Power;
import static org.matheclipse.core.expression.F.Sec;
import static org.matheclipse.core.expression.F.Sin;
import static org.matheclipse.core.expression.F.Sow;
import static org.matheclipse.core.expression.F.Times;
import static org.matheclipse.core.expression.F.a;
import static org.matheclipse.core.expression.F.x;
import static org.matheclipse.core.expression.F.y;

import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.reflection.system.Expand;
import org.matheclipse.core.reflection.system.ExpandAll;

/**
 * 
 */
public class ExpandTestCases extends AbstractTestCase {
	public ExpandTestCases(String name) {
		super(name);
	}

	public void testExpand001() {
		IAST ast = Times(x, x);
		IExpr temp = ExpandAll.expandAll(ast, null, false, false);
		assertEquals(temp.toString(), "x^2");
	}

	public void testExpand002() {
		IAST ast = Times(x, Times(C1D2, x));
		IExpr temp = Expand.expand(ast, null, false, false);
		assertEquals(temp.toString(), "x^2/2");
	}

	public void testExpand003() {
		IAST ast = Power(Plus(x, y), C3);
		IExpr temp = ExpandAll.expandAll(ast, null, false, false);
		assertEquals(temp.toString(), "x^3+y^3+3*x^2*y+3*x*y^2");
	}

	public void testExpand004() {
		IAST ast = Plus(Sow(Power(a, 2)), C1);
		IExpr temp = ExpandAll.expandAll(ast, null, false, false);
		if (!temp.isPresent()) {
			temp = ast;
		}
		assertEquals(temp.toString(), "1+Sow[a^2]");
	}

	public void testExpand005() {
		// x / y
		IAST ast = Times(x, Power(y, -1));
		IExpr temp = ExpandAll.expandAll(ast, null, true, false);
		// because of sorting and flattening flags:
		assertEquals(temp, F.NIL);

		// temp = ExpandAll.expandAll((IAST)temp, null, true, false);
		// assertNull(temp);
	}

	public void testExpand006() {
		// (3*x^2+2)^2
		IAST ast = Power(Plus(C2, Times(C3, Power(x, 2))), C2);
		IExpr temp = Expand.expand(ast, null, true, false);
		if (temp == null) {
			temp = ast;
		}
		assertEquals(temp.toString(), "4+9*x^4+12*x^2");
	}

	public void testExpand007() {
		// Sec(x)^2*Sin(x)^2
		IAST ast = Times(Power(Sec(x), C2), Power(Sin(x), 2));
		IExpr temp = Expand.expand(ast, null, true, false);
		if (!temp.isPresent()) {
			assertEquals(ast.toString(), "Sin[x]^2/Cos[x]^2");
			return;
		}
		assertEquals(temp.toString(), "Sin[x]^2/Cos[x]^2");
	}

	public void testRationalFunction001() {
		check("PolynomialQ(x^2*(a+b*x^3)^16,x)", "True");
	}

}