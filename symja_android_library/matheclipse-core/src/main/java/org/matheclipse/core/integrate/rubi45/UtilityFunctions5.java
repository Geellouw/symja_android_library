package org.matheclipse.core.integrate.rubi45;


import static org.matheclipse.core.expression.F.*;
import static org.matheclipse.core.integrate.rubi45.UtilityFunctionCtors.*;

import org.matheclipse.core.interfaces.IAST;
/** 
 * UtilityFunctions rules from the <a href="http://www.apmaths.uwo.ca/~arich/">Rubi -
 * rule-based integrator</a>.
 *  
 */
public class UtilityFunctions5 { 
  public static IAST RULES = List( 
ISetDelayed(SimplifyAntiderivative(ArcTanh(Times(a_DEFAULT,Tan(u_))),x_Symbol),
    Condition(RectifyTangent(u,Times(CI,a),Negate(CI),x),And(And(FreeQ(a,x),PositiveQ(Sqr(a))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcCoth(Times(a_DEFAULT,Tan(u_))),x_Symbol),
    Condition(RectifyTangent(u,Times(CI,a),Negate(CI),x),And(And(FreeQ(a,x),PositiveQ(Sqr(a))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTanh(Tanh(u_)),x_Symbol),
    SimplifyAntiderivative(u,x)),
ISetDelayed(SimplifyAntiderivative(ArcCoth(Tanh(u_)),x_Symbol),
    SimplifyAntiderivative(u,x)),
ISetDelayed(SimplifyAntiderivative(ArcCot(Times(a_DEFAULT,Cot(u_))),x_Symbol),
    Condition(RectifyCotangent(u,a,C1,x),And(And(FreeQ(a,x),PositiveQ(Sqr(a))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTan(Times(a_DEFAULT,Cot(u_))),x_Symbol),
    Condition(RectifyCotangent(u,a,CN1,x),And(And(FreeQ(a,x),PositiveQ(Sqr(a))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTan(Times(a_DEFAULT,Coth(u_))),x_Symbol),
    Condition(Negate(SimplifyAntiderivative(ArcTan(Times(Tanh(u),Power(a,-1))),x)),And(FreeQ(a,x),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcCoth(Times(a_DEFAULT,Cot(u_))),x_Symbol),
    Condition(RectifyCotangent(u,Times(CI,a),CI,x),And(And(FreeQ(a,x),PositiveQ(Sqr(a))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTanh(Times(a_DEFAULT,Cot(u_))),x_Symbol),
    Condition(RectifyCotangent(u,Times(CI,a),CI,x),And(And(FreeQ(a,x),PositiveQ(Sqr(a))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcCoth(Coth(u_)),x_Symbol),
    SimplifyAntiderivative(u,x)),
ISetDelayed(SimplifyAntiderivative(ArcTanh(Times(a_DEFAULT,Coth(u_))),x_Symbol),
    Condition(SimplifyAntiderivative(ArcTanh(Times(Tanh(u),Power(a,-1))),x),And(FreeQ(a,x),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTanh(Coth(u_)),x_Symbol),
    SimplifyAntiderivative(u,x)),
ISetDelayed(SimplifyAntiderivative(ArcTan(Times(c_DEFAULT,Plus(a_,Times(b_DEFAULT,Tan(u_))))),x_Symbol),
    Condition(RectifyTangent(u,Times(a,c),Times(b,c),C1,x),And(And(And(FreeQ(List(a,b,c),x),PositiveQ(Times(Sqr(a),Sqr(c)))),PositiveQ(Times(Sqr(b),Sqr(c)))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTanh(Times(c_DEFAULT,Plus(a_,Times(b_DEFAULT,Tan(u_))))),x_Symbol),
    Condition(RectifyTangent(u,Times(CI,a,c),Times(CI,b,c),Negate(CI),x),And(And(And(FreeQ(List(a,b,c),x),PositiveQ(Times(Sqr(a),Sqr(c)))),PositiveQ(Times(Sqr(b),Sqr(c)))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTan(Times(c_DEFAULT,Plus(a_,Times(b_DEFAULT,Cot(u_))))),x_Symbol),
    Condition(RectifyCotangent(u,Times(a,c),Times(b,c),C1,x),And(And(And(FreeQ(List(a,b,c),x),PositiveQ(Times(Sqr(a),Sqr(c)))),PositiveQ(Times(Sqr(b),Sqr(c)))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTanh(Times(c_DEFAULT,Plus(a_,Times(b_DEFAULT,Cot(u_))))),x_Symbol),
    Condition(RectifyCotangent(u,Times(CI,a,c),Times(CI,b,c),Negate(CI),x),And(And(And(FreeQ(List(a,b,c),x),PositiveQ(Times(Sqr(a),Sqr(c)))),PositiveQ(Times(Sqr(b),Sqr(c)))),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTan(Plus(a_DEFAULT,Times(c_DEFAULT,Sqr(Tan(u_))),Times(b_DEFAULT,Tan(u_)))),x_Symbol),
    Condition(If(EvenQ(Denominator(NumericFactor(Together(u)))),ArcTan(NormalizeTogether(Times(Plus(a,c,Negate(C1),Times(Plus(a,Negate(c),Negate(C1)),Cos(Times(C2,u))),Times(b,Sin(Times(C2,u)))),Power(Plus(a,c,C1,Times(Plus(a,Negate(c),C1),Cos(Times(C2,u))),Times(b,Sin(Times(C2,u)))),-1)))),ArcTan(NormalizeTogether(Times(Plus(c,Times(Plus(a,Negate(c),Negate(C1)),Sqr(Cos(u))),Times(b,Cos(u),Sin(u))),Power(Plus(c,Times(Plus(a,Negate(c),C1),Sqr(Cos(u))),Times(b,Cos(u),Sin(u))),-1))))),And(FreeQ(List(a,b,c),x),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTan(Plus(a_DEFAULT,Times(b_DEFAULT,Plus(d_DEFAULT,Times(e_DEFAULT,Tan(u_)))),Times(c_DEFAULT,Sqr(Plus(f_DEFAULT,Times(g_DEFAULT,Tan(u_))))))),x_Symbol),
    Condition(SimplifyAntiderivative(ArcTan(Plus(a,Times(b,d),Times(c,Sqr(f)),Times(Plus(Times(b,e),Times(C2,c,f,g)),Tan(u)),Times(c,Sqr(g),Sqr(Tan(u))))),x),And(FreeQ(List(a,b,c),x),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTan(Plus(a_DEFAULT,Times(c_DEFAULT,Sqr(Tan(u_))))),x_Symbol),
    Condition(If(EvenQ(Denominator(NumericFactor(Together(u)))),ArcTan(NormalizeTogether(Times(Plus(a,c,Negate(C1),Times(Plus(a,Negate(c),Negate(C1)),Cos(Times(C2,u)))),Power(Plus(a,c,C1,Times(Plus(a,Negate(c),C1),Cos(Times(C2,u)))),-1)))),ArcTan(NormalizeTogether(Times(Plus(c,Times(Plus(a,Negate(c),Negate(C1)),Sqr(Cos(u)))),Power(Plus(c,Times(Plus(a,Negate(c),C1),Sqr(Cos(u)))),-1))))),And(FreeQ(List(a,c),x),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(ArcTan(Plus(a_DEFAULT,Times(c_DEFAULT,Sqr(Plus(f_DEFAULT,Times(g_DEFAULT,Tan(u_))))))),x_Symbol),
    Condition(SimplifyAntiderivative(ArcTan(Plus(a,Times(c,Sqr(f)),Times(C2,c,f,g,Tan(u)),Times(c,Sqr(g),Sqr(Tan(u))))),x),And(FreeQ(List(a,c),x),ComplexFreeQ(u)))),
ISetDelayed(SimplifyAntiderivative(u_,x_Symbol),
    If(FreeQ(u,x),C0,If(LogQ(u),Log(RemoveContent(Part(u,C1),x)),If(SumQ(u),SimplifyAntiderivativeSum(Map(Function(SimplifyAntiderivative(Slot1,x)),u),x),u)))),
ISetDelayed(SimplifyAntiderivativeSum(Plus(v_DEFAULT,Times(A_DEFAULT,Log(Plus(a_,Times(b_DEFAULT,Power(Tan(u_),n_DEFAULT))))),Times(B_DEFAULT,Log(Cos(u_)))),x_Symbol),
    Condition(Plus(SimplifyAntiderivativeSum(v,x),Times(ASymbol,Log(RemoveContent(Plus(Times(a,Power(Cos(u),n)),Times(b,Power(Sin(u),n))),x)))),And(And(FreeQ(List(a,b,ASymbol,BSymbol),x),IntegerQ(n)),ZeroQ(Plus(Times(n,ASymbol),Negate(BSymbol)))))),
ISetDelayed(SimplifyAntiderivativeSum(Plus(v_DEFAULT,Times(A_DEFAULT,Log(Plus(a_,Times(b_DEFAULT,Power(Cot(u_),n_DEFAULT))))),Times(B_DEFAULT,Log(Sin(u_)))),x_Symbol),
    Condition(Plus(SimplifyAntiderivativeSum(v,x),Times(ASymbol,Log(RemoveContent(Plus(Times(a,Power(Sin(u),n)),Times(b,Power(Cos(u),n))),x)))),And(And(FreeQ(List(a,b,ASymbol,BSymbol),x),IntegerQ(n)),ZeroQ(Plus(Times(n,ASymbol),Negate(BSymbol)))))),
ISetDelayed(SimplifyAntiderivativeSum(Plus(v_DEFAULT,Times(A_DEFAULT,Log(Plus(a_,Times(b_DEFAULT,Power(Tan(u_),n_DEFAULT))))),Times(B_DEFAULT,Log(Plus(c_,Times(d_DEFAULT,Power(Tan(u_),n_DEFAULT)))))),x_Symbol),
    Condition(Plus(SimplifyAntiderivativeSum(v,x),Times(ASymbol,Log(RemoveContent(Plus(Times(a,Power(Cos(u),n)),Times(b,Power(Sin(u),n))),x))),Times(BSymbol,Log(RemoveContent(Plus(Times(c,Power(Cos(u),n)),Times(d,Power(Sin(u),n))),x)))),And(And(FreeQ(List(a,b,c,d,ASymbol,BSymbol),x),IntegerQ(n)),ZeroQ(Plus(ASymbol,BSymbol))))),
ISetDelayed(SimplifyAntiderivativeSum(Plus(v_DEFAULT,Times(A_DEFAULT,Log(Plus(a_,Times(b_DEFAULT,Power(Cot(u_),n_DEFAULT))))),Times(B_DEFAULT,Log(Plus(c_,Times(d_DEFAULT,Power(Cot(u_),n_DEFAULT)))))),x_Symbol),
    Condition(Plus(SimplifyAntiderivativeSum(v,x),Times(ASymbol,Log(RemoveContent(Plus(Times(b,Power(Cos(u),n)),Times(a,Power(Sin(u),n))),x))),Times(BSymbol,Log(RemoveContent(Plus(Times(d,Power(Cos(u),n)),Times(c,Power(Sin(u),n))),x)))),And(And(FreeQ(List(a,b,c,d,ASymbol,BSymbol),x),IntegerQ(n)),ZeroQ(Plus(ASymbol,BSymbol))))),
ISetDelayed(SimplifyAntiderivativeSum(Plus(v_DEFAULT,Times(A_DEFAULT,Log(Plus(a_,Times(b_DEFAULT,Power(Tan(u_),n_DEFAULT))))),Times(B_DEFAULT,Log(Plus(c_,Times(d_DEFAULT,Power(Tan(u_),n_DEFAULT))))),Times(C_DEFAULT,Log(Plus(e_,Times(f_DEFAULT,Power(Tan(u_),n_DEFAULT)))))),x_Symbol),
    Condition(Plus(SimplifyAntiderivativeSum(v,x),Times(ASymbol,Log(RemoveContent(Plus(Times(a,Power(Cos(u),n)),Times(b,Power(Sin(u),n))),x))),Times(BSymbol,Log(RemoveContent(Plus(Times(c,Power(Cos(u),n)),Times(d,Power(Sin(u),n))),x))),Times(CSymbol,Log(RemoveContent(Plus(Times(e,Power(Cos(u),n)),Times(f,Power(Sin(u),n))),x)))),And(And(FreeQ(List(a,b,c,d,e,f,ASymbol,BSymbol,CSymbol),x),IntegerQ(n)),ZeroQ(Plus(ASymbol,BSymbol,CSymbol))))),
ISetDelayed(SimplifyAntiderivativeSum(Plus(v_DEFAULT,Times(A_DEFAULT,Log(Plus(a_,Times(b_DEFAULT,Power(Cot(u_),n_DEFAULT))))),Times(B_DEFAULT,Log(Plus(c_,Times(d_DEFAULT,Power(Cot(u_),n_DEFAULT))))),Times(C_DEFAULT,Log(Plus(e_,Times(f_DEFAULT,Power(Cot(u_),n_DEFAULT)))))),x_Symbol),
    Condition(Plus(SimplifyAntiderivativeSum(v,x),Times(ASymbol,Log(RemoveContent(Plus(Times(b,Power(Cos(u),n)),Times(a,Power(Sin(u),n))),x))),Times(BSymbol,Log(RemoveContent(Plus(Times(d,Power(Cos(u),n)),Times(c,Power(Sin(u),n))),x))),Times(CSymbol,Log(RemoveContent(Plus(Times(f,Power(Cos(u),n)),Times(e,Power(Sin(u),n))),x)))),And(And(FreeQ(List(a,b,c,d,e,f,ASymbol,BSymbol,CSymbol),x),IntegerQ(n)),ZeroQ(Plus(ASymbol,BSymbol,CSymbol))))),
ISetDelayed(SimplifyAntiderivativeSum(u_,x_Symbol),
    u),
ISetDelayed(RectifyTangent(u_,a_,b_,x_Symbol),
    If(MatchQ(Together(a),Times(d_DEFAULT,Complex(C0,c_))),Module(List(Set(c,Times(a,Power(CI,-1))),e),If(NegativeQ(c),RectifyTangent(u,Negate(a),Negate(b),x),If(ZeroQ(Plus(c,Negate(C1))),If(EvenQ(Denominator(NumericFactor(Together(u)))),Times(CI,b,C1D2,ArcTanh(Sin(Times(C2,u)))),Times(CI,b,C1D2,ArcTanh(Times(C2,Cos(u),Sin(u))))),CompoundExpression(CompoundExpression(Set(e,SmartDenominator(c)),Set(c,Times(c,e))),Plus(Times(CI,b,C1D2,Log(RemoveContent(Plus(Times(e,Cos(u)),Times(c,Sin(u))),x))),Times(CN1,CI,b,C1D2,Log(RemoveContent(Plus(Times(e,Cos(u)),Times(CN1,c,Sin(u))),x)))))))),If(NegativeQ(a),RectifyTangent(u,Negate(a),Negate(b),x),If(ZeroQ(Plus(a,Negate(C1))),Times(b,SimplifyAntiderivative(u,x)),Module(List(c,$s("numr"),$s("denr")),If(EvenQ(Denominator(NumericFactor(Together(u)))),CompoundExpression(CompoundExpression(CompoundExpression(Set(c,Simplify(Times(Plus(C1,a),Power(Plus(C1,Negate(a)),-1)))),Set($s("numr"),SmartNumerator(c))),Set($s("denr"),SmartDenominator(c))),Plus(Times(b,SimplifyAntiderivative(u,x)),Times(CN1,b,ArcTan(NormalizeLeadTermSigns(Times($s("denr"),Sin(Times(C2,u)),Power(Plus($s("numr"),Times($s("denr"),Cos(Times(C2,u)))),-1))))))),If(PositiveQ(Plus(a,Negate(C1))),CompoundExpression(CompoundExpression(CompoundExpression(Set(c,Simplify(Power(Plus(a,Negate(C1)),-1))),Set($s("numr"),SmartNumerator(c))),Set($s("denr"),SmartDenominator(c))),Plus(Times(b,SimplifyAntiderivative(u,x)),Times(b,ArcTan(NormalizeLeadTermSigns(Times($s("denr"),Cos(u),Sin(u),Power(Plus($s("numr"),Times($s("denr"),Sqr(Sin(u)))),-1))))))),CompoundExpression(CompoundExpression(CompoundExpression(Set(c,Simplify(Times(a,Power(Plus(C1,Negate(a)),-1)))),Set($s("numr"),SmartNumerator(c))),Set($s("denr"),SmartDenominator(c))),Plus(Times(b,SimplifyAntiderivative(u,x)),Times(CN1,b,ArcTan(NormalizeLeadTermSigns(Times($s("denr"),Cos(u),Sin(u),Power(Plus($s("numr"),Times($s("denr"),Sqr(Cos(u)))),-1)))))))))))))),
ISetDelayed(RectifyTangent(u_,a_,b_,r_,x_Symbol),
    If(And(MatchQ(Together(a),Times(d_DEFAULT,Complex(C0,c_))),MatchQ(Together(b),Times(d_DEFAULT,Complex(C0,c_)))),Module(List(Set(c,Times(a,Power(CI,-1))),Set(d,Times(b,Power(CI,-1))),e),If(NegativeQ(d),RectifyTangent(u,Negate(a),Negate(b),Negate(r),x),CompoundExpression(CompoundExpression(CompoundExpression(Set(e,SmartDenominator(Together(Plus(c,Times(d,x))))),Set(c,Times(c,e))),Set(d,Times(d,e))),If(EvenQ(Denominator(NumericFactor(Together(u)))),Plus(Times(CI,r,C1D4,Log(RemoveContent(Plus(Simplify(Plus(Sqr(Plus(c,e)),Sqr(d))),Times(Simplify(Plus(Sqr(Plus(c,e)),Negate(Sqr(d)))),Cos(Times(C2,u))),Times(Simplify(Times(C2,Plus(c,e),d)),Sin(Times(C2,u)))),x))),Times(CN1,CI,r,C1D4,Log(RemoveContent(Plus(Simplify(Plus(Sqr(Plus(c,Negate(e))),Sqr(d))),Times(Simplify(Plus(Sqr(Plus(c,Negate(e))),Negate(Sqr(d)))),Cos(Times(C2,u))),Times(Simplify(Times(C2,Plus(c,Negate(e)),d)),Sin(Times(C2,u)))),x)))),Plus(Times(CI,r,C1D4,Log(RemoveContent(Plus(Simplify(Sqr(Plus(c,e))),Times(Simplify(Times(C2,Plus(c,e),d)),Cos(u),Sin(u)),Times(CN1,Simplify(Plus(Sqr(Plus(c,e)),Negate(Sqr(d)))),Sqr(Sin(u)))),x))),Times(CN1,CI,r,C1D4,Log(RemoveContent(Plus(Simplify(Sqr(Plus(c,Negate(e)))),Times(Simplify(Times(C2,Plus(c,Negate(e)),d)),Cos(u),Sin(u)),Times(CN1,Simplify(Plus(Sqr(Plus(c,Negate(e))),Negate(Sqr(d)))),Sqr(Sin(u)))),x)))))))),If(NegativeQ(b),RectifyTangent(u,Negate(a),Negate(b),Negate(r),x),If(EvenQ(Denominator(NumericFactor(Together(u)))),Plus(Times(r,SimplifyAntiderivative(u,x)),Times(r,ArcTan(Simplify(Times(Plus(Times(C2,a,b,Cos(Times(C2,u))),Times(CN1,Plus(C1,Sqr(a),Negate(Sqr(b))),Sin(Times(C2,u)))),Power(Plus(Sqr(a),Sqr(Plus(C1,b)),Times(Plus(C1,Sqr(a),Negate(Sqr(b))),Cos(Times(C2,u))),Times(C2,a,b,Sin(Times(C2,u)))),-1)))))),Plus(Times(r,SimplifyAntiderivative(u,x)),Times(CN1,r,ArcTan(ActivateTrig(Simplify(Times(Plus(Times(a,b),Times(CN1,C2,a,b,Sqr($($s("§cos"),u))),Times(Plus(C1,Sqr(a),Negate(Sqr(b))),$($s("§cos"),u),$($s("§sin"),u))),Power(Plus(Times(b,Plus(C1,b)),Times(Plus(C1,Sqr(a),Negate(Sqr(b))),Sqr($($s("§cos"),u))),Times(C2,a,b,$($s("§cos"),u),$($s("§sin"),u))),-1))))))))))),
ISetDelayed(RectifyCotangent(u_,a_,b_,x_Symbol),
    If(MatchQ(Together(a),Times(d_DEFAULT,Complex(C0,c_))),Module(List(Set(c,Times(a,Power(CI,-1))),e),If(NegativeQ(c),RectifyCotangent(u,Negate(a),Negate(b),x),If(ZeroQ(Plus(c,Negate(C1))),If(EvenQ(Denominator(NumericFactor(Together(u)))),Times(CN1,CI,b,C1D2,ArcTanh(Sin(Times(C2,u)))),Times(CN1,CI,b,C1D2,ArcTanh(Times(C2,Cos(u),Sin(u))))),CompoundExpression(CompoundExpression(Set(e,SmartDenominator(c)),Set(c,Times(c,e))),Plus(Times(CN1,CI,b,C1D2,Log(RemoveContent(Plus(Times(c,Cos(u)),Times(e,Sin(u))),x))),Times(CI,b,C1D2,Log(RemoveContent(Plus(Times(c,Cos(u)),Times(CN1,e,Sin(u))),x)))))))),If(NegativeQ(a),RectifyCotangent(u,Negate(a),Negate(b),x),If(ZeroQ(Plus(a,Negate(C1))),Times(b,SimplifyAntiderivative(u,x)),Module(List(c,$s("numr"),$s("denr")),If(EvenQ(Denominator(NumericFactor(Together(u)))),CompoundExpression(CompoundExpression(CompoundExpression(Set(c,Simplify(Times(Plus(C1,a),Power(Plus(C1,Negate(a)),-1)))),Set($s("numr"),SmartNumerator(c))),Set($s("denr"),SmartDenominator(c))),Plus(Times(b,SimplifyAntiderivative(u,x)),Times(b,ArcTan(NormalizeLeadTermSigns(Times($s("denr"),Sin(Times(C2,u)),Power(Plus($s("numr"),Times(CN1,$s("denr"),Cos(Times(C2,u)))),-1))))))),If(PositiveQ(Plus(a,Negate(C1))),CompoundExpression(CompoundExpression(CompoundExpression(Set(c,Simplify(Power(Plus(a,Negate(C1)),-1))),Set($s("numr"),SmartNumerator(c))),Set($s("denr"),SmartDenominator(c))),Plus(Times(b,SimplifyAntiderivative(u,x)),Times(CN1,b,ArcTan(NormalizeLeadTermSigns(Times($s("denr"),Cos(u),Sin(u),Power(Plus($s("numr"),Times($s("denr"),Sqr(Cos(u)))),-1))))))),CompoundExpression(CompoundExpression(CompoundExpression(Set(c,Simplify(Times(a,Power(Plus(C1,Negate(a)),-1)))),Set($s("numr"),SmartNumerator(c))),Set($s("denr"),SmartDenominator(c))),Plus(Times(b,SimplifyAntiderivative(u,x)),Times(b,ArcTan(NormalizeLeadTermSigns(Times($s("denr"),Cos(u),Sin(u),Power(Plus($s("numr"),Times($s("denr"),Sqr(Sin(u)))),-1)))))))))))))),
ISetDelayed(RectifyCotangent(u_,a_,b_,r_,x_Symbol),
    If(And(MatchQ(Together(a),Times(d_DEFAULT,Complex(C0,c_))),MatchQ(Together(b),Times(d_DEFAULT,Complex(C0,c_)))),Module(List(Set(c,Times(a,Power(CI,-1))),Set(d,Times(b,Power(CI,-1))),e),If(NegativeQ(d),RectifyTangent(u,Negate(a),Negate(b),Negate(r),x),CompoundExpression(CompoundExpression(CompoundExpression(Set(e,SmartDenominator(Together(Plus(c,Times(d,x))))),Set(c,Times(c,e))),Set(d,Times(d,e))),If(EvenQ(Denominator(NumericFactor(Together(u)))),Plus(Times(CI,r,C1D4,Log(RemoveContent(Plus(Simplify(Plus(Sqr(Plus(c,e)),Sqr(d))),Times(CN1,Simplify(Plus(Sqr(Plus(c,e)),Negate(Sqr(d)))),Cos(Times(C2,u))),Times(Simplify(Times(C2,Plus(c,e),d)),Sin(Times(C2,u)))),x))),Times(CN1,CI,r,C1D4,Log(RemoveContent(Plus(Simplify(Plus(Sqr(Plus(c,Negate(e))),Sqr(d))),Times(CN1,Simplify(Plus(Sqr(Plus(c,Negate(e))),Negate(Sqr(d)))),Cos(Times(C2,u))),Times(Simplify(Times(C2,Plus(c,Negate(e)),d)),Sin(Times(C2,u)))),x)))),Plus(Times(CI,r,C1D4,Log(RemoveContent(Plus(Simplify(Sqr(Plus(c,e))),Times(CN1,Simplify(Plus(Sqr(Plus(c,e)),Negate(Sqr(d)))),Sqr(Cos(u))),Times(Simplify(Times(C2,Plus(c,e),d)),Cos(u),Sin(u))),x))),Times(CN1,CI,r,C1D4,Log(RemoveContent(Plus(Simplify(Sqr(Plus(c,Negate(e)))),Times(CN1,Simplify(Plus(Sqr(Plus(c,Negate(e))),Negate(Sqr(d)))),Sqr(Cos(u))),Times(Simplify(Times(C2,Plus(c,Negate(e)),d)),Cos(u),Sin(u))),x)))))))),If(NegativeQ(b),RectifyCotangent(u,Negate(a),Negate(b),Negate(r),x),If(EvenQ(Denominator(NumericFactor(Together(u)))),Plus(Times(CN1,r,SimplifyAntiderivative(u,x)),Times(CN1,r,ArcTan(Simplify(Times(Plus(Times(C2,a,b,Cos(Times(C2,u))),Times(Plus(C1,Sqr(a),Negate(Sqr(b))),Sin(Times(C2,u)))),Power(Plus(Sqr(a),Sqr(Plus(C1,b)),Times(CN1,Plus(C1,Sqr(a),Negate(Sqr(b))),Cos(Times(C2,u))),Times(C2,a,b,Sin(Times(C2,u)))),-1)))))),Plus(Times(CN1,r,SimplifyAntiderivative(u,x)),Times(CN1,r,ArcTan(ActivateTrig(Simplify(Times(Plus(Times(a,b),Times(CN1,C2,a,b,Sqr($($s("§sin"),u))),Times(Plus(C1,Sqr(a),Negate(Sqr(b))),$($s("§cos"),u),$($s("§sin"),u))),Power(Plus(Times(b,Plus(C1,b)),Times(Plus(C1,Sqr(a),Negate(Sqr(b))),Sqr($($s("§sin"),u))),Times(C2,a,b,$($s("§cos"),u),$($s("§sin"),u))),-1))))))))))),
ISetDelayed(SmartNumerator(Power(u_,n_)),
    Condition(SmartDenominator(Power(u,Negate(n))),And(RationalQ(n),Less(n,C0)))),
ISetDelayed(SmartNumerator(Times(u_,v_)),
    Times(SmartNumerator(u),SmartNumerator(v))),
ISetDelayed(SmartNumerator(u_),
    Numerator(u)),
ISetDelayed(SmartDenominator(Power(u_,n_)),
    Condition(SmartNumerator(Power(u,Negate(n))),And(RationalQ(n),Less(n,C0)))),
ISetDelayed(SmartDenominator(Times(u_,v_)),
    Times(SmartDenominator(u),SmartDenominator(v))),
ISetDelayed(SmartDenominator(u_),
    Denominator(u)),
ISetDelayed(SubstFor(w_,v_,u_,x_),
    SimplifyIntegrand(Times(w,SubstFor(v,u,x)),x)),
ISetDelayed(SubstFor(v_,u_,x_),
    If(AtomQ(v),Subst(u,v,x),If(Not(InertTrigFreeQ(u)),SubstFor(v,ActivateTrig(u),x),If(Not(OneQ(FreeFactors(v,x))),SubstFor(NonfreeFactors(v,x),u,Times(x,Power(FreeFactors(v,x),-1))),If(SinQ(v),SubstForTrig(u,x,Sqrt(Plus(C1,Negate(Sqr(x)))),Part(v,C1),x),If(CosQ(v),SubstForTrig(u,Sqrt(Plus(C1,Negate(Sqr(x)))),x,Part(v,C1),x),If(TanQ(v),SubstForTrig(u,Times(x,Power(Plus(C1,Sqr(x)),CN1D2)),Power(Plus(C1,Sqr(x)),CN1D2),Part(v,C1),x),If(CotQ(v),SubstForTrig(u,Power(Plus(C1,Sqr(x)),CN1D2),Times(x,Power(Plus(C1,Sqr(x)),CN1D2)),Part(v,C1),x),If(SecQ(v),SubstForTrig(u,Power(Plus(C1,Negate(Sqr(x))),CN1D2),Power(x,-1),Part(v,C1),x),If(CscQ(v),SubstForTrig(u,Power(x,-1),Power(Plus(C1,Negate(Sqr(x))),CN1D2),Part(v,C1),x),If(SinhQ(v),SubstForHyperbolic(u,x,Sqrt(Plus(C1,Sqr(x))),Part(v,C1),x),If(CoshQ(v),SubstForHyperbolic(u,Sqrt(Plus(CN1,Sqr(x))),x,Part(v,C1),x),If(TanhQ(v),SubstForHyperbolic(u,Times(x,Power(Plus(C1,Negate(Sqr(x))),CN1D2)),Power(Plus(C1,Negate(Sqr(x))),CN1D2),Part(v,C1),x),If(CothQ(v),SubstForHyperbolic(u,Power(Plus(CN1,Sqr(x)),CN1D2),Times(x,Power(Plus(CN1,Sqr(x)),CN1D2)),Part(v,C1),x),If(SechQ(v),SubstForHyperbolic(u,Power(Plus(CN1,Sqr(x)),CN1D2),Power(x,-1),Part(v,C1),x),If(CschQ(v),SubstForHyperbolic(u,Power(x,-1),Power(Plus(C1,Sqr(x)),CN1D2),Part(v,C1),x),SubstForAux(u,v,x))))))))))))))))),
ISetDelayed(SubstForAux(u_,v_,x_),
    If(SameQ(u,v),x,If(AtomQ(u),u,If(And(PowerQ(u),FreeQ(Part(u,C2),x)),If(ZeroQ(Plus(Part(u,C1),Negate(v))),Power(x,Part(u,C2)),If(And(And(PowerQ(v),FreeQ(Part(v,C2),x)),ZeroQ(Plus(Part(u,C1),Negate(Part(v,C1))))),Power(x,Simplify(Times(Part(u,C2),Power(Part(v,C2),-1)))),Power(SubstForAux(Part(u,C1),v,x),Part(u,C2)))),If(And(ProductQ(u),Not(OneQ(FreeFactors(u,x)))),Times(FreeFactors(u,x),SubstForAux(NonfreeFactors(u,x),v,x)),If(And(ProductQ(u),ProductQ(v)),SubstForAux(First(u),First(v),x),Map(Function(SubstForAux(Slot1,v,x)),u))))))),
ISetDelayed(SubstForTrig(u_,$p("§sin"),$p("§cos"),v_,x_),
    If(AtomQ(u),u,If(And(TrigQ(u),IntegerQuotientQ(Part(u,C1),v)),If(Or(SameQ(Part(u,C1),v),ZeroQ(Plus(Part(u,C1),Negate(v)))),If(SinQ(u),$s("§sin"),If(CosQ(u),$s("§cos"),If(TanQ(u),Times($s("§sin"),Power($s("§cos"),-1)),If(CotQ(u),Times($s("§cos"),Power($s("§sin"),-1)),If(SecQ(u),Power($s("§cos"),-1),Power($s("§sin"),-1)))))),Map(Function(SubstForTrig(Slot1,$s("§sin"),$s("§cos"),v,x)),ReplaceAll(TrigExpand($(Head(u),Times(Simplify(Times(Part(u,C1),Power(v,-1))),x))),Rule(x,v)))),If(And(And(And(And(ProductQ(u),CosQ(Part(u,C1))),SinQ(Part(u,C2))),ZeroQ(Plus(Part(u,C1,C1),Times(CN1,C1D2,v)))),ZeroQ(Plus(Part(u,C2,C1),Times(CN1,C1D2,v)))),Times(C1D2,$s("§sin"),SubstForTrig(Drop(u,C2),$s("§sin"),$s("§cos"),v,x)),Map(Function(SubstForTrig(Slot1,$s("§sin"),$s("§cos"),v,x)),u))))),
ISetDelayed(SubstForHyperbolic(u_,$p("§sinh"),$p("§cosh"),v_,x_),
    If(AtomQ(u),u,If(And(HyperbolicQ(u),IntegerQuotientQ(Part(u,C1),v)),If(Or(SameQ(Part(u,C1),v),ZeroQ(Plus(Part(u,C1),Negate(v)))),If(SinhQ(u),$s("§sinh"),If(CoshQ(u),$s("§cosh"),If(TanhQ(u),Times($s("§sinh"),Power($s("§cosh"),-1)),If(CothQ(u),Times($s("§cosh"),Power($s("§sinh"),-1)),If(SechQ(u),Power($s("§cosh"),-1),Power($s("§sinh"),-1)))))),Map(Function(SubstForHyperbolic(Slot1,$s("§sinh"),$s("§cosh"),v,x)),ReplaceAll(TrigExpand($(Head(u),Times(Simplify(Times(Part(u,C1),Power(v,-1))),x))),Rule(x,v)))),If(And(And(And(And(ProductQ(u),CoshQ(Part(u,C1))),SinhQ(Part(u,C2))),ZeroQ(Plus(Part(u,C1,C1),Times(CN1,C1D2,v)))),ZeroQ(Plus(Part(u,C2,C1),Times(CN1,C1D2,v)))),Times(C1D2,$s("§sinh"),SubstForHyperbolic(Drop(u,C2),$s("§sinh"),$s("§cosh"),v,x)),Map(Function(SubstForHyperbolic(Slot1,$s("§sinh"),$s("§cosh"),v,x)),u))))),
ISetDelayed(SubstForFractionalPowerOfLinear(u_,x_Symbol),
    Module(List(Set($s("lst"),FractionalPowerOfLinear(u,C1,False,x)),n,a,b,$s("tmp")),If(Or(FalseQ($s("lst")),FalseQ(Part($s("lst"),C2))),False,CompoundExpression(CompoundExpression(CompoundExpression(CompoundExpression(CompoundExpression(Set(n,Part($s("lst"),C1)),Set(a,Coefficient(Part($s("lst"),C2),x,C0))),Set(b,Coefficient(Part($s("lst"),C2),x,C1))),Set($s("tmp"),Times(Power(x,Plus(n,Negate(C1))),SubstForFractionalPower(u,Part($s("lst"),C2),n,Plus(Times(CN1,a,Power(b,-1)),Times(Power(x,n),Power(b,-1))),x)))),Set($s("tmp"),SplitFreeFactors(Simplify($s("tmp")),x))),List(Part($s("tmp"),C2),n,Part($s("lst"),C2),Times(Part($s("tmp"),C1),Power(b,-1))))))),
ISetDelayed(FractionalPowerOfLinear(u_,n_,v_,x_),
    If(Or(AtomQ(u),FreeQ(u,x)),List(n,v),If(CalculusQ(u),False,If(And(And(FractionalPowerQ(u),LinearQ(Part(u,C1),x)),Or(FalseQ(v),ZeroQ(Plus(Part(u,C1),Negate(v))))),List(LCM(Denominator(Part(u,C2)),n),Part(u,C1)),Catch(Module(List(Set($s("lst"),List(n,v))),CompoundExpression(Scan(Function(If(FalseQ(Set($s("lst"),FractionalPowerOfLinear(Slot1,Part($s("lst"),C1),Part($s("lst"),C2),x))),Throw(False))),u),$s("lst")))))))),
ISetDelayed(InverseFunctionOfLinear(u_,x_Symbol),
    If(Or(Or(AtomQ(u),CalculusQ(u)),FreeQ(u,x)),False,If(And(InverseFunctionQ(u),LinearQ(Part(u,C1),x)),u,Module(List($s("tmp")),Catch(CompoundExpression(Scan(Function(If(NotFalseQ(Set($s("tmp"),InverseFunctionOfLinear(Slot1,x))),Throw($s("tmp")))),u),False)))))),
ISetDelayed(TryPureTanSubst(u_,x_Symbol),
    Not(MatchQ(u,Condition($(F_,Times(c_DEFAULT,Plus(a_DEFAULT,Times(b_DEFAULT,$(G_,v_))))),And(And(And(FreeQ(List(a,b,c),x),MemberQ(List($s("ArcTan"),$s("ArcCot"),$s("ArcTanh"),$s("ArcCoth")),FSymbol)),MemberQ(List($s("Tan"),$s("Cot"),$s("Tanh"),$s("Coth")),GSymbol)),LinearQ(v,x)))))),
ISetDelayed(TryTanhSubst(u_,x_Symbol),
    And(And(And(And(And(And(FalseQ(FunctionOfLinear(u,x)),Not(MatchQ(u,Condition(Times(r_DEFAULT,Power(Plus(s_,t_),n_DEFAULT)),And(IntegerQ(n),Greater(n,C0)))))),Not(MatchQ(u,Log(v_)))),Not(MatchQ(u,Condition(Power(Plus(a_,Times(b_DEFAULT,Power($(f_,x),n_))),-1),And(And(SinhCoshQ(f),IntegerQ(n)),Greater(n,C2)))))),Not(MatchQ(u,Condition(Times($(f_,Times(m_DEFAULT,x)),$(g_,Times(n_DEFAULT,x))),And(And(IntegersQ(m,n),SinhCoshQ(f)),SinhCoshQ(g)))))),Not(MatchQ(u,Condition(Times(r_DEFAULT,Power(Times(a_DEFAULT,Power(s_,m_)),p_)),And(FreeQ(List(a,m,p),x),Not(And(SameQ(m,C2),Or(SameQ(s,Sech(x)),SameQ(s,Csch(x)))))))))),SameQ(u,ExpandIntegrand(u,x)))),
ISetDelayed(TryPureTanhSubst(u_,x_Symbol),
    And(And(And(And(And(Not(MatchQ(u,Log(v_))),Not(MatchQ(u,Condition(ArcTanh(Times(a_DEFAULT,Tanh(v_))),FreeQ(a,x))))),Not(MatchQ(u,Condition(ArcTanh(Times(a_DEFAULT,Coth(v_))),FreeQ(a,x))))),Not(MatchQ(u,Condition(ArcCoth(Times(a_DEFAULT,Tanh(v_))),FreeQ(a,x))))),Not(MatchQ(u,Condition(ArcCoth(Times(a_DEFAULT,Coth(v_))),FreeQ(a,x))))),SameQ(u,ExpandIntegrand(u,x)))),
ISetDelayed(InertTrigQ(f_),
    MemberQ(List($s("§sin"),$s("§cos"),$s("§tan"),$s("§cot"),$s("§sec"),$s("§csc")),f)),
ISetDelayed(InertTrigQ(f_,g_),
    If(SameQ(f,g),InertTrigQ(f),Or(InertReciprocalQ(f,g),InertReciprocalQ(g,f)))),
ISetDelayed(InertTrigQ(f_,g_,h_),
    And(InertTrigQ(f,g),InertTrigQ(g,h))),
ISetDelayed(InertReciprocalQ(f_,g_),
    Or(Or(And(SameQ(f,$s("§sin")),SameQ(g,$s("§csc"))),And(SameQ(f,$s("§cos")),SameQ(g,$s("§sec")))),And(SameQ(f,$s("§tan")),SameQ(g,$s("§cot"))))),
ISetDelayed(InertTrigFreeQ(u_),
    And(And(And(And(And(FreeQ(u,$s("§sin")),FreeQ(u,$s("§cos"))),FreeQ(u,$s("§tan"))),FreeQ(u,$s("§cot"))),FreeQ(u,$s("§sec"))),FreeQ(u,$s("§csc")))),
ISetDelayed(ActivateTrig(u_),
    ReplaceAll(u,List(Rule($s("§sin"),$s("Sin")),Rule($s("§cos"),$s("Cos")),Rule($s("§tan"),$s("Tan")),Rule($s("§cot"),$s("Cot")),Rule($s("§sec"),$s("Sec")),Rule($s("§csc"),$s("Csc"))))),
ISetDelayed(DeactivateTrig(u_,x_),
    FixInertTrigFunction(DeactivateTrigAux(u,x),x)),
ISetDelayed(DeactivateTrigAux(u_,x_),
    If(AtomQ(u),u,If(And(TrigQ(u),LinearQ(Part(u,C1),x)),Module(List(Set(v,ExpandToSum(Part(u,C1),x))),If(SinQ(u),$($s("§sin"),v),If(CosQ(u),$($s("§cos"),v),If(TanQ(u),$($s("§tan"),v),If(CotQ(u),$($s("§cot"),v),If(SecQ(u),$($s("§sec"),v),$($s("§csc"),v))))))),If(And(HyperbolicQ(u),LinearQ(Part(u,C1),x)),Module(List(Set(v,ExpandToSum(Times(CI,Part(u,C1)),x))),If(SinhQ(u),Times(CN1,CI,$($s("§sin"),v)),If(CoshQ(u),$($s("§cos"),v),If(TanhQ(u),Times(CN1,CI,$($s("§tan"),v)),If(CothQ(u),Times(CI,$($s("§cot"),v)),If(SechQ(u),$($s("§sec"),v),Times(CI,$($s("§csc"),v)))))))),Map(Function(DeactivateTrigAux(Slot1,x)),u))))),
ISetDelayed(FixInertTrigFunction(Times(a_,u_),x_),
    Condition(Times(a,FixInertTrigFunction(u,x)),FreeQ(a,x))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,Power(Times(a_,Plus(b_,v_)),n_)),x_),
    Condition(FixInertTrigFunction(Times(u,Power(Plus(Times(a,b),Times(a,v)),n)),x),And(FreeQ(List(a,b,n),x),Not(FreeQ(v,x))))),
ISetDelayed(FixInertTrigFunction(Times(Power(Times(c_DEFAULT,$($s("§sin"),w_)),n_DEFAULT),Power($($s("§sec"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(Power($($s("§cos"),v),Negate(m)),Power(Times(c,$($s("§sin"),w)),n)),x),And(FreeQ(List(c,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(Power(Times(c_DEFAULT,$($s("§sin"),w_)),n_DEFAULT),Power($($s("§csc"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(Power($($s("§sin"),v),Negate(m)),Power(Times(c,$($s("§sin"),w)),n)),x),And(FreeQ(List(c,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(Power(Times(c_DEFAULT,$($s("§cos"),w_)),n_DEFAULT),Power($($s("§sec"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(Power($($s("§cos"),v),Negate(m)),Power(Times(c,$($s("§cos"),w)),n)),x),And(FreeQ(List(c,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(Power(Times(c_DEFAULT,$($s("§cos"),w_)),n_DEFAULT),Power($($s("§csc"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(Power($($s("§sin"),v),Negate(m)),Power(Times(c,$($s("§cos"),w)),n)),x),And(FreeQ(List(c,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(Power($($s("§sec"),v_),m_DEFAULT),Power($($s("§sec"),w_),n_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(Power($($s("§cos"),v),Negate(m)),Power($($s("§cos"),w),Negate(n))),x),IntegersQ(m,n))),
ISetDelayed(FixInertTrigFunction(Times(Power($($s("§csc"),v_),m_DEFAULT),Power($($s("§csc"),w_),n_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(Power($($s("§sin"),v),Negate(m)),Power($($s("§sin"),w),Negate(n))),x),IntegersQ(m,n))),
ISetDelayed(FixInertTrigFunction(Times(Power(Plus(a_,Times(b_DEFAULT,Power(Times(c_DEFAULT,$($s("§sin"),w_)),p_))),n_DEFAULT),Power($($s("§cot"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(Power($($s("§tan"),v),Negate(m)),Power(Plus(a,Times(b,Power(Times(c,$($s("§sin"),w)),p))),n)),x),And(FreeQ(List(a,b,c,n,p),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,Power(Plus(a_,Times(b_DEFAULT,$($s("§sin"),w_))),n_DEFAULT),Power($($s("§tan"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§sin"),v),m),Power(Power($($s("§cos"),v),m),-1),Power(Plus(a,Times(b,$($s("§sin"),w))),n)),x),And(FreeQ(List(a,b,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,Power(Plus(a_,Times(b_DEFAULT,$($s("§sin"),w_))),n_DEFAULT),Power($($s("§cot"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cos"),v),m),Power(Power($($s("§sin"),v),m),-1),Power(Plus(a,Times(b,$($s("§sin"),w))),n)),x),And(FreeQ(List(a,b,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§sec"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cos"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§sin"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§csc"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§sin"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§sin"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(Power(Plus(a_,Times(b_DEFAULT,Power(Times(c_DEFAULT,$($s("§cos"),w_)),p_))),n_DEFAULT),Power($($s("§tan"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(Power($($s("§cot"),v),Negate(m)),Power(Plus(a,Times(b,Power(Times(c,$($s("§cos"),w)),p))),n)),x),And(FreeQ(List(a,b,c,n,p),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,Power(Plus(a_,Times(b_DEFAULT,$($s("§cos"),w_))),n_DEFAULT),Power($($s("§tan"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§sin"),v),m),Power(Power($($s("§cos"),v),m),-1),Power(Plus(a,Times(b,$($s("§cos"),w))),n)),x),And(FreeQ(List(a,b,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,Power(Plus(a_,Times(b_DEFAULT,$($s("§cos"),w_))),n_DEFAULT),Power($($s("§cot"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cos"),v),m),Power(Power($($s("§sin"),v),m),-1),Power(Plus(a,Times(b,$($s("§cos"),w))),n)),x),And(FreeQ(List(a,b,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§sec"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cos"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§cos"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§csc"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§sin"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§cos"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§cot"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§tan"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§tan"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§sec"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cos"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§tan"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§csc"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§sin"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§tan"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§tan"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cot"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§cot"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§sec"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cos"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§cot"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§csc"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§sin"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§cot"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§cos"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§sec"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§sec"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§cot"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§tan"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§sec"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§csc"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§sin"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§sec"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§sin"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§csc"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§csc"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§tan"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cot"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§csc"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,w_,Power($($s("§sec"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cos"),v),Negate(m)),w),x),And(InertTrigSumQ(w,$s("§csc"),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,Power(Plus(Times(b_DEFAULT,$($s("§cos"),v_)),Times(a_DEFAULT,$($s("§sin"),v_))),n_DEFAULT),Power($($s("§tan"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§sin"),v),m),Power($($s("§cos"),v),Negate(m)),Power(Plus(Times(a,$($s("§sin"),v)),Times(b,$($s("§cos"),v))),n)),x),And(FreeQ(List(a,b,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,Power(Plus(Times(b_DEFAULT,$($s("§cos"),v_)),Times(a_DEFAULT,$($s("§sin"),v_))),n_DEFAULT),Power($($s("§cot"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cos"),v),m),Power($($s("§sin"),v),Negate(m)),Power(Plus(Times(a,$($s("§sin"),v)),Times(b,$($s("§cos"),v))),n)),x),And(FreeQ(List(a,b,n),x),IntegerQ(m)))),
ISetDelayed(FixInertTrigFunction(Times(u_DEFAULT,Power(Plus(Times(b_DEFAULT,$($s("§cos"),v_)),Times(a_DEFAULT,$($s("§sin"),v_))),n_DEFAULT),Power($($s("§sec"),v_),m_DEFAULT)),x_),
    Condition(FixInertTrigFunction(Times(u,Power($($s("§cos"),v),Negate(m)),Power(Plus(Times(a,$($s("§sin"),v)),Times(b,$($s("§cos"),v))),n)),x),And(FreeQ(List(a,b,n),x),IntegerQ(m))))
  );
}
