// $Id: CssImage.java,v 1.2 2012-11-08 14:06:59 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @author CSS3 Image
 */
public class CssImage extends CssValue {

	public static final int type = CssTypes.CSS_IMAGE;

	public final int getType() {
		return type;
	}

	static final CssIdent to = CssIdent.getIdent("to");
	static final CssIdent left = CssIdent.getIdent("left");
	static final CssIdent right = CssIdent.getIdent("right");
	static final CssIdent top = CssIdent.getIdent("top");
	static final CssIdent bottom = CssIdent.getIdent("bottom");

	public static boolean isVerticalIdent(CssIdent ident) {
		return ident.equals(top) || ident.equals(bottom);
	}

	public static CssIdent getLinearGradientIdent(CssIdent ident) {
		if (left.equals(ident)) {
			return left;
		}
		if (right.equals(ident)) {
			return right;
		}
		if (top.equals(ident)) {
			return top;
		}
		if (bottom.equals(ident)) {
			return bottom;
		}
		return null;
	}

	String name;
	CssValue value;

	private String _cache;

	/**
	 * Set the value of this function
	 *
	 * @param s  the string representation of the frequency.
	 * @param ac For errors and warnings reports.
	 */
	public void set(String s, ApplContext ac) {
		// @@TODO
	}

	/**
	 * @param exp
	 * @param ac
	 * @throws InvalidParamException
	 * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#image-list-type
	 */
	public void setImageList(CssExpression exp, ApplContext ac)
			throws InvalidParamException {
		name = "image";
		_cache = null;
		// ImageList defined in CSS3 and onward
		if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(name).append('(').append(exp.toStringFromStart()).append(')');
			throw new InvalidParamException("notversion", sb.toString(),
					ac.getCssVersionString(), ac);
		}

		CssValue val;
		char op;
		boolean gotcolor = false;
		ArrayList<CssValue> v = new ArrayList<CssValue>();
		CssColor c;
		while (!exp.end()) {
			val = exp.getValue();
			op = exp.getOperator();
			// color is always last
			if (gotcolor) {
				throw new InvalidParamException("value",
						val.toString(),
						"image()", ac);
			}

			switch (val.getType()) {
				case CssTypes.CSS_URL:
				case CssTypes.CSS_STRING:
					v.add(val);
					break;
				case CssTypes.CSS_HASH_IDENT:
					c = new CssColor();
					c.setShortRGBColor(val.toString(), ac);
					v.add(c);
					gotcolor = true;
					break;
				case CssTypes.CSS_IDENT:
					c = new CssColor();
					c.setIdentColor(val.toString(), ac);
					v.add(c);
					gotcolor = true;
					break;
				default:
					throw new InvalidParamException("value",
							val.toString(),
							"image()", ac);
			}
			exp.next();
			if (!exp.end() && op != COMMA) {
				exp.starts();
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
		}
		value = (v.size() == 1) ? v.get(0) : new CssLayerList(v);
	}

	/**
	 * @param exp
	 * @param ac
	 * @throws InvalidParamException
	 * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#linear-gradient-type
	 */
	public void setLinearGradient(CssExpression exp, ApplContext ac)
			throws InvalidParamException {
		name = "linear-gradient";
		_cache = null;
		// ImageList defined in CSS3 and onward
		if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(name).append('(').append(exp.toStringFromStart()).append(')');
			throw new InvalidParamException("notversion", sb.toString(),
					ac.getCssVersionString(), ac);
		}
		ArrayList<CssValue> v = new ArrayList<CssValue>();
		CssValue val = exp.getValue();
		char op = exp.getOperator();

		if (val.getType() == CssTypes.CSS_ANGLE) {
			v.add(val);
			if (op != COMMA) {
				exp.starts();
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			exp.next();
		} else if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent ident = (CssIdent) val;
			if (to.equals(ident)) {
				CssValueList vl = new CssValueList();
				vl.add(to);
				// we must now eat one or two valid idents
				// this is boringly boring...
				CssIdent v1 = null;
				CssIdent v2 = null;
				if (op != SPACE) {
					exp.starts();
					throw new InvalidParamException("operator",
							((new Character(op)).toString()), ac);
				}
				exp.next();
				if (exp.end()) {
					throw new InvalidParamException("few-value", name, ac);
				}
				val = exp.getValue();
				op = exp.getOperator();
				boolean isV1Vertical, isV2Vertical;
				if (val.getType() != CssTypes.CSS_IDENT) {
					throw new InvalidParamException("value",
							val.toString(),
							name, ac);
				}
				v1 = getLinearGradientIdent((CssIdent) val);
				if (v1 == null) {
					throw new InvalidParamException("value",
							val.toString(),
							name, ac);
				}
				vl.add(v1);
				isV1Vertical = isVerticalIdent(v1);
				exp.next();
				if (exp.end()) {
					throw new InvalidParamException("few-value", name, ac);
				}
				if (op == SPACE) {
					// the operator is a space, we should have
					// another
					val = exp.getValue();
					op = exp.getOperator();
					if (val.getType() != CssTypes.CSS_IDENT) {
						throw new InvalidParamException("value",
								val.toString(),
								name, ac);
					}
					v2 = getLinearGradientIdent((CssIdent) val);
					if (v2 == null) {
						throw new InvalidParamException("value",
								val.toString(),
								name, ac);
					}
					isV2Vertical = isVerticalIdent(v2);
					if ((isV1Vertical && isV2Vertical) ||
							(!isV1Vertical && !isV2Vertical)) {
						throw new InvalidParamException("value",
								val.toString(),
								name, ac);
					}
					vl.add(v2);
					exp.next();
				}
				v.add(vl);
				if (op != COMMA) {
					exp.starts();
					throw new InvalidParamException("operator",
							((new Character(op)).toString()), ac);
				}
			}
		}
		// now we a list of at least two color stops.
		ArrayList<CssValue> stops = parseColorStops(exp, ac);
		if (stops.size() < 2) {
			throw new InvalidParamException("few-value", name, ac);
		}

		v.addAll(stops);
		value = new CssLayerList(v);
	}

	private final ArrayList<CssValue> parseColorStops(CssExpression expression,
													  ApplContext ac)
			throws InvalidParamException {
		ArrayList<CssValue> v = new ArrayList<CssValue>();
		CssValue val;
		char op;
		CssColor stopcol;
		CssValue stopval;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_HASH_IDENT:
					stopcol = new CssColor();
					stopcol.setShortRGBColor(val.toString(), ac);
					break;
				case CssTypes.CSS_IDENT:
					stopcol = new CssColor();
					stopcol.setIdentColor(val.toString(), ac);
					break;
				case CssTypes.CSS_COLOR:
					stopcol = (CssColor) val;
					break;
				default:
					throw new InvalidParamException("value", val.toString(),
							"color", ac);
			}
			if (op == SPACE && expression.getRemainingCount() > 1) {
				expression.next();
				stopval = expression.getValue();
				op = expression.getOperator();

				switch (stopval.getType()) {
					case CssTypes.CSS_NUMBER:
						val.getLength();
					case CssTypes.CSS_LENGTH:
					case CssTypes.CSS_PERCENTAGE:
						ArrayList<CssValue> stop = new ArrayList<CssValue>(2);
						stop.add(stopcol);
						stop.add(stopval);
						v.add(new CssValueList(stop));
						break;
					default:
						throw new InvalidParamException("value", val.toString(),
								"color-stop", ac);
				}
			} else {
				v.add(stopcol);
			}
			expression.next();
			if (!expression.end() && op != COMMA) {
				expression.starts();
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
		}
		return v;
	}

	/**
	 * Returns the value
	 */
	public Object get() {
		// @@TODO
		return null;
	}

	/**
	 * Returns the name of the function
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a string representation of the object.
	 */
	public String toString() {
		if (_cache == null) {
			StringBuilder sb = new StringBuilder();
			sb.append(name).append('(').append(value).append(')');
			_cache = sb.toString();
		}
		return _cache;
	}

	/**
	 * Compares two values for equality.
	 *
	 * @param other The other value.
	 */
	public boolean equals(Object other) {
		// @@FIXME
		return (other instanceof CssImage &&
				this.name.equals(((CssImage) other).name) &&
				this.value.equals(((CssImage) other).value));
	}
}