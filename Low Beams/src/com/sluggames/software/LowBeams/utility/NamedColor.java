/*
 * MIT License
 *
 * Copyright (c) 2018 Slug Games
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.sluggames.software.LowBeams.utility;

import javafx.scene.paint.Color;

/**
 * For some bizarre reason, the developers of JavaFX went through all the
 * trouble of defining numerous static named colors, but didn't take the time to
 * put them in an enumeration. Having an enumeration of named colors is useful
 * for things like populating a choice box without having to manually insert
 * each individual named color.
 *
 *
 * @author david.boeger@sluggames.com
 *
 * @version 0.4.0
 * @since 0.4.0
 */
public enum NamedColor {
	/*
		**************
		*** VALUES ***
		**************
	*/
	ALICEBLUE		(Color.ALICEBLUE,		"Alice Blue"),
	ANTIQUEWHITE		(Color.ANTIQUEWHITE,		"Antique White"),
	AQUA			(Color.AQUA,			"Aqua"),
	AQUAMARINE		(Color.AQUAMARINE,		"Aquamarine"),
	AZURE			(Color.AZURE,			"Azure"),
	BEIGE			(Color.BEIGE,			"Beige"),
	BISQUE			(Color.BISQUE,			"Bisque"),
	BLACK			(Color.BLACK,			"Black"),
	BLANCHEDALMOND		(Color.BLANCHEDALMOND,		"Blanched Almond"),
	BLUE			(Color.BLUE,			"Blue"),
	BLUEVIOLET		(Color.BLUEVIOLET,		"Blue Violet"),
	BROWN			(Color.BROWN,			"Brown"),
	BURLYWOOD		(Color.BURLYWOOD,		"Burly Wood"),
	CADETBLUE		(Color.CADETBLUE,		"Cadet Blue"),
	CHARTREUSE		(Color.CHARTREUSE,		"Chartreuse"),
	CHOCOLATE		(Color.CHOCOLATE,		"Chocolate"),
	CORAL			(Color.CORAL,			"Coral"),
	CORNFLOWERBLUE		(Color.CORNFLOWERBLUE,		"Cornflower Blue"),
	CORNSILK		(Color.CORNSILK,		"Corn Silk"),
	CRIMSON			(Color.CRIMSON,			"Crimson"),
	CYAN			(Color.CYAN,			"Cyan"),
	DARKBLUE		(Color.DARKBLUE,		"Dark Blue"),
	DARKCYAN		(Color.DARKCYAN,		"Dark Cyan"),
	DARKGOLDENROD		(Color.DARKGOLDENROD,		"Dark Goldenrod"),
	DARKGRAY		(Color.DARKGRAY,		"Dark Gray"),
	DARKGREEN		(Color.DARKGREEN,		"Dark Green"),
	DARKGREY		(Color.DARKGREY,		"Dark Grey"),
	DARKKHAKI		(Color.DARKKHAKI,		"Dark Khaki"),
	DARKMAGENTA		(Color.DARKMAGENTA,		"Dark Magenta"),
	DARKOLIVEGREEN		(Color.DARKOLIVEGREEN,		"Dark Olive Green"),
	DARKORANGE		(Color.DARKORANGE,		"Dark Orange"),
	DARKORCHID		(Color.DARKORCHID,		"Dark Orchid"),
	DARKRED			(Color.DARKRED,			"Dark Red"),
	DARKSALMON		(Color.DARKSALMON,		"Dark Salmon"),
	DARKSEAGREEN		(Color.DARKSEAGREEN,		"Dark Sea Green"),
	DARKSLATEBLUE		(Color.DARKSLATEBLUE,		"Dark Slate Blue"),
	DARKSLATEGRAY		(Color.DARKSLATEGRAY,		"Dark Slate Gray"),
	DARKSLATEGREY		(Color.DARKSLATEGREY,		"Dark Slate Grey"),
	DARKTURQUOISE		(Color.DARKTURQUOISE,		"Dark Turquoise"),
	DARKVIOLET		(Color.DARKVIOLET,		"Dark Violet"),
	DEEPPINK		(Color.DEEPPINK,		"Deep Pink"),
	DEEPSKYBLUE		(Color.DEEPSKYBLUE,		"Deep Sky Blue"),
	DIMGRAY			(Color.DIMGRAY,			"Dim Gray"),
	DIMGREY			(Color.DIMGREY,			"Dim Grey"),
	DODGERBLUE		(Color.DODGERBLUE,		"Dodger Blue"),
	FIREBRICK		(Color.FIREBRICK,		"Firebrick"),
	FLORALWHITE		(Color.FLORALWHITE,		"Floral White"),
	FORESTGREEN		(Color.FORESTGREEN,		"Forest Green"),
	FUCHSIA			(Color.FUCHSIA,			"Fuchsia"),
	GAINSBORO		(Color.GAINSBORO,		"Gainsboro"),
	GHOSTWHITE		(Color.GHOSTWHITE,		"Ghost White"),
	GOLD			(Color.GOLD,			"Gold"),
	GOLDENROD		(Color.GOLDENROD,		"Goldenrod"),
	GRAY			(Color.GRAY,			"Gray"),
	GREEN			(Color.GREEN,			"Green"),
	GREENYELLOW		(Color.GREENYELLOW,		"Green Yellow"),
	GREY			(Color.GREY,			"Grey"),
	HONEYDEW		(Color.HONEYDEW,		"Honeydew"),
	HOTPINK			(Color.HOTPINK,			"Hot Pink"),
	INDIANRED		(Color.INDIANRED,		"Indian Red"),
	INDIGO			(Color.INDIGO,			"Indigo"),
	IVORY			(Color.IVORY,			"Ivory"),
	KHAKI			(Color.KHAKI,			"Khaki"),
	LAVENDER		(Color.LAVENDER,		"Lavender"),
	LAVENDERBLUSH		(Color.LAVENDERBLUSH,		"Lavender Blush"),
	LAWNGREEN		(Color.LAWNGREEN,		"Lawn Green"),
	LEMONCHIFFON		(Color.LEMONCHIFFON,		"Lemon Chiffon"),
	LIGHTBLUE		(Color.LIGHTBLUE,		"Light Blue"),
	LIGHTCORAL		(Color.LIGHTCORAL,		"Light Coral"),
	LIGHTCYAN		(Color.LIGHTCYAN,		"Light Cyan"),
	LIGHTGOLDENRODYELLOW	(Color.LIGHTGOLDENRODYELLOW,	"Light Goldenrod Yellow"),
	LIGHTGRAY		(Color.LIGHTGRAY,		"Light Gray"),
	LIGHTGREEN		(Color.LIGHTGREEN,		"Light Green"),
	LIGHTGREY		(Color.LIGHTGREY,		"Light Grey"),
	LIGHTPINK		(Color.LIGHTPINK,		"Light Pink"),
	LIGHTSALMON		(Color.LIGHTSALMON,		"Light Salmon"),
	LIGHTSEAGREEN		(Color.LIGHTSEAGREEN,		"Light Sea Green"),
	LIGHTSKYBLUE		(Color.LIGHTSKYBLUE,		"Light Sky Blue"),
	LIGHTSLATEGRAY		(Color.LIGHTSLATEGRAY,		"Light Slate Gray"),
	LIGHTSLATEGREY		(Color.LIGHTSLATEGREY,		"Light Slate Grey"),
	LIGHTSTEELBLUE		(Color.LIGHTSTEELBLUE,		"Light Steel Blue"),
	LIGHTYELLOW		(Color.LIGHTYELLOW,		"Light Yellow"),
	LIME			(Color.LIME,			"Lime"),
	LIMEGREEN		(Color.LIMEGREEN,		"Lime Green"),
	LINEN			(Color.LINEN,			"Linen"),
	MAGENTA			(Color.MAGENTA,			"Magenta"),
	MAROON			(Color.MAROON,			"Maroon"),
	MEDIUMAQUAMARINE	(Color.MEDIUMAQUAMARINE,	"Medium Aquamarine"),
	MEDIUMBLUE		(Color.MEDIUMBLUE,		"Medium Blue"),
	MEDIUMORCHID		(Color.MEDIUMORCHID,		"Medium Orchid"),
	MEDIUMPURPLE		(Color.MEDIUMPURPLE,		"Medium Purple"),
	MEDIUMSEAGREEN		(Color.MEDIUMSEAGREEN,		"Medium Sea Green"),
	MEDIUMSLATEBLUE		(Color.MEDIUMSLATEBLUE,		"Medium Slate Blue"),
	MEDIUMSPRINGGREEN	(Color.MEDIUMSPRINGGREEN,	"Medium Spring Green"),
	MEDIUMTURQUOISE		(Color.MEDIUMTURQUOISE,		"Medium Turquoise"),
	MEDIUMVIOLETRED		(Color.MEDIUMVIOLETRED,		"Medium Violet Red"),
	MIDNIGHTBLUE		(Color.MIDNIGHTBLUE,		"Midnight Blue"),
	MINTCREAM		(Color.MINTCREAM,		"Mint Cream"),
	MISTYROSE		(Color.MISTYROSE,		"Misty Rose"),
	MOCCASIN		(Color.MOCCASIN,		"Mocassin"),
	NAVAJOWHITE		(Color.NAVAJOWHITE,		"Navajo White"),
	NAVY			(Color.NAVY,			"Navy"),
	OLDLACE			(Color.OLDLACE,			"Old Lace"),
	OLIVE			(Color.OLIVE,			"Olive"),
	OLIVEDRAB		(Color.OLIVEDRAB,		"Olive Drab"),
	ORANGE			(Color.ORANGE,			"Orange"),
	ORANGERED		(Color.ORANGERED,		"Orange Red"),
	ORCHID			(Color.ORCHID,			"Orchid"),
	PALEGOLDENROD		(Color.PALEGOLDENROD,		"Pale Goldenrod"),
	PALEGREEN		(Color.PALEGREEN,		"Pale Green"),
	PALETURQUOISE		(Color.PALETURQUOISE,		"Pale Turquoise"),
	PALEVIOLETRED		(Color.PALEVIOLETRED,		"Pale Violet Red"),
	PAPAYAWHIP		(Color.PAPAYAWHIP,		"Papaya Whip"),
	PEACHPUFF		(Color.PEACHPUFF,		"Peach Puff"),
	PERU			(Color.PERU,			"Peru"),
	PINK			(Color.PINK,			"Pink"),
	PLUM			(Color.PLUM,			"Plum"),
	POWDERBLUE		(Color.POWDERBLUE,		"Powder Blue"),
	PURPLE			(Color.PURPLE,			"Purple"),
	RED			(Color.RED,			"Red"),
	ROSYBROWN		(Color.ROSYBROWN,		"Rosy Brown"),
	ROYALBLUE		(Color.ROYALBLUE,		"Royal Blue"),
	SADDLEBROWN		(Color.SADDLEBROWN,		"Saddle Brown"),
	SALMON			(Color.SALMON,			"Salmon"),
	SANDYBROWN		(Color.SANDYBROWN,		"Sandy Brown"),
	SEAGREEN		(Color.SEAGREEN,		"Sea Green"),
	SEASHELL		(Color.SEASHELL,		"Sea Shell"),
	SIENNA			(Color.SIENNA,			"Sienna"),
	SILVER			(Color.SILVER,			"Silver"),
	SKYBLUE			(Color.SKYBLUE,			"Sky Blue"),
	SLATEBLUE		(Color.SLATEBLUE,		"Slate Blue"),
	SLATEGRAY		(Color.SLATEGRAY,		"Slate Gray"),
	SLATEGREY		(Color.SLATEGREY,		"Slate Grey"),
	SNOW			(Color.SNOW,			"Snow"),
	SPRINGGREEN		(Color.SPRINGGREEN,		"Spring Green"),
	STEELBLUE		(Color.STEELBLUE,		"Steel Blue"),
	TAN			(Color.TAN,			"Tan"),
	TEAL			(Color.TEAL,			"Teal"),
	THISTLE			(Color.THISTLE,			"Thistle"),
	TOMATO			(Color.TOMATO,			"Tomato"),
	TRANSPARENT		(Color.TRANSPARENT,		"Transparent"),
	TURQUOISE		(Color.TURQUOISE,		"Turquoise"),
	VIOLET			(Color.VIOLET,			"Violet"),
	WHEAT			(Color.WHEAT,			"Wheat"),
	WHITE			(Color.WHITE,			"White"),
	WHITESMOKE		(Color.WHITESMOKE,		"White Smoke"),
	YELLOW			(Color.YELLOW,			"Yellow"),
	YELLOWGREEN		(Color.YELLOWGREEN,		"Yellow Green");


	/*
		*************
		*** COLOR ***
		*************
	*/
	private final Color color;

	/*
			\\\\\\\
			\ GET \
			\\\\\\\
	*/
	public Color getColor() {
		return color;
	}


	/*
		************
		*** NAME ***
		************
	*/
	private final String name;

	/*
			\\\\\\\
			\ GET \
			\\\\\\\
	*/
	public String getName() {
		return name;
	}

	/*
			\\\\\\\\\\\\\
			\ TO STRING \
			\\\\\\\\\\\\\
	*/
	@Override
	public String toString() {
		return name;
	}


	/*
		********************
		*** CONSTRUCTION ***
		********************
	*/
	NamedColor(Color color, String name) {
		/*
		Validate arguments.
		*/
		if (color == null) {
			throw new NullPointerException(
			    "color == null"
			);
		}
		if (name == null) {
			throw new NullPointerException(
			    "name == null"
			);
		}

		this.color = color;
		this.name = name;
	}
}