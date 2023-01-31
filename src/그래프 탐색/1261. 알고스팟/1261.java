import jdk.internal.util.xml.impl.Input;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
	private static int row;
	private static int col;
	private static char[][] matrix;

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		matrix = new char[row][col];

		for (int r = 0; r < row; r++) {
			String tmp = br.readLine();
			for (int c = 0; c < col; c++) {
				matrix[r][c] = tmp.charAt(c);
			}
		}

	}

}

class Square {
	private Point leftUpper;
	private Point leftLower;
	private Point rightLower;
	private Point rightUpper;

	public Point getLeftUpper() {
		return this.leftUpper;
	}

	public Point getLeftLower() {
		return this.leftLower;
	}

	public Point getRightLower() {
		return this.rightLower;
	}

	public Point getRightUpper() {
		return this.rightUpper;
	}
}

class Point {
	private int x;
	private int y;

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
