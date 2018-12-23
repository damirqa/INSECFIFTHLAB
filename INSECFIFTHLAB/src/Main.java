public class Main {

	static char[] word = "абсурд".toCharArray();
	static char[] alphabet = " абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
	
	static int[] Te = new int[word.length];
	
	static int[] B1 = new int[Te.length / 2]; 
	static int[] B2 = new int[Te.length / 2];
	
	static int[] C1 = new int[B1.length];
	static int[] C2 = new int[B2.length];
	
	static int[][] A = {{6, 10, 11},
						{4, 5, 8},
						{9, 5, 1}};
	static int detA = 0;
	
	static int[] T1 = new int[Te.length];
	
	static int[][] addedA = new int[3][3];
	static double[][] inverseMatrix = new double[3][3];
	
	
	public static void main(String[] args) {
		
		System.out.println("Слово:");
		for (int i = 0; i < word.length; i++)
			System.out.print(word[i]);
		System.out.print("\n\n");
		
		/* ШИФРОВАНИЕ
		 * Определяем числовой эквивалент исходного слово,
		 * как последовательность соответствующих порядковых номеров букв слова
		 */
		for (int a = 1; a < alphabet.length; a++) {
			for (int w = 0; w < word.length; w++) {
				if (alphabet[a] == word[w]) {
					Te[w] = a;
				}
			}
		}
		
		System.out.println("Числовой эквивалент:");
		for (int i = 0; i < Te.length; i++)
			System.out.print(Te[i] + " ");
		System.out.print("\n\n");
		
		/*
		 * Делим массив Te на две части (B1 и B2)
		 */
		for (int i = 0; i < Te.length / 2; i++) {
			B1[i] = Te[i];
			B2[i] = Te[i + 3];
		}
		
		/*
		 * Результат умножения матрицы на вектора B1 и B2 записываются в массивы С1 и C2.
		 * Массив T1 есть зашифрованное слово
		 */
		for (int i = 0; i < C1.length; i++) {
			C1[i] = A[i][0] * B1[0] + A[i][1] * B1[1] + A[i][2] * B1[2];
			C2[i] = A[i][0] * B2[0] + A[i][1] * B2[1] + A[i][2] * B2[2];
			
			T1[i] = C1[i];
			T1[i + 3] = C2[i];
		}
		
		System.out.println("Зашифрованное слово:");
		for(int i = 0; i < T1.length; i++)
			System.out.print(T1[i] + " ");
		System.out.print("\n\n");
		
		/* РАСШИФРОВАНИЕ
		 * Находим определитель |A|
		 */
		detA = A[0][0] * (A[1][1] * A[2][2] - A[2][1]  * A[1][2]) - A[0][1] * (A[1][0] * A[2][2] - A[2][0] * A[1][2]) + A[0][2] * (A[1][0] * A[2][1] - A[2][0] * A[1][1]);
		
		System.out.println("Определитель |A|: \n" + detA + "\n");
		
		/*
		 * Определяется присоединенная матрица А*, каждый элемент которой является алгебраическим дополнением элемента A[i][j] матрицы А:
		 */
		addedA[0][0] = (int) (Math.pow(-1, 1 + 1) * (A[1][1] * A[2][2] - A[2][1] * A[1][2]));
		addedA[0][1] = (int) (Math.pow(-1, 1 + 2) * (A[1][0] * A[2][2] - A[2][0] * A[1][2]));
		addedA[0][2] = (int) (Math.pow(-1, 1 + 3) * (A[1][0] * A[2][1] - A[2][0] * A[1][1]));
		addedA[1][0] = (int) (Math.pow(-1, 2 + 1) * (A[0][1] * A[2][2] - A[2][1] * A[0][2]));
		addedA[1][1] = (int) (Math.pow(-1, 2 + 2) * (A[0][0] * A[2][2] - A[2][0] * A[0][2]));
		addedA[1][2] = (int) (Math.pow(-1, 2 + 3) * (A[0][0] * A[2][1] - A[2][0] * A[0][1]));
		addedA[2][0] = (int) (Math.pow(-1, 3 + 1) * (A[0][1] * A[1][2] - A[1][1] * A[0][2]));
		addedA[2][1] = (int) (Math.pow(-1, 3 + 2) * (A[0][0] * A[1][2] - A[1][0] * A[0][2]));
		addedA[2][2] = (int) (Math.pow(-1, 3 + 3) * (A[0][0] * A[1][1] - A[1][0] * A[0][1]));
		
		System.out.println("Присоединеннная матрица А*:");
		for(int i = 0; i < addedA.length; i++) {
			for(int j = 0; j < addedA.length; j++) {
				System.out.print(addedA[i][j] + " ");
			}
			System.out.print("\n");
		}
		
		
		/*
		 * Переворачиваем матрицу (транспонированная матрица)
		 */
		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < 3; j++) {
				int start = addedA[i][j];
				int end = addedA[j][i];
				addedA[i][j] = end;
				addedA[j][i] = start;	
			}
		}
		
		System.out.println("\nТранспонированная матрица:");
		for(int i = 0; i < addedA.length; i++) {
			for(int j = 0; j < addedA.length; j++) {
				System.out.print(addedA[i][j] + " ");
			}
			System.out.print("\n");
		}
			
		/*
		 * Обратная матрица
		 */
		System.out.println("\nОбратная матрица:");
		for (int i = 0; i < inverseMatrix.length; i++) {
			for (int j = 0; j < inverseMatrix.length; j++) {
				inverseMatrix[i][j] = (double) addedA[i][j] / detA;
				System.out.print(inverseMatrix[i][j] + " ");
			}
			System.out.print("\n");
		}
		
		/*
		 * Дешифруем
		 */
		System.out.println("\nДешифруем:");
		for (int i = 0; i < C1.length; i++) {
			B1[i] = (int) Math.round(inverseMatrix[i][0] * C1[0] + inverseMatrix[i][1] * C1[1] + inverseMatrix[i][2] * C1[2]);
			B2[i] = (int) Math.round(inverseMatrix[i][0] * C2[0] + inverseMatrix[i][1] * C2[1] + inverseMatrix[i][2] * C2[2]);
			
			System.out.printf("B1[%d] = %f * %d + %f * %d + %f + %d\n", i, inverseMatrix[i][0], C1[0], inverseMatrix[i][1], C1[1], inverseMatrix[i][2], C1[2]);
			System.out.printf("B2[%d] = %f * %d + %f * %d + %f + %d\n", i, inverseMatrix[i][0], C2[0], inverseMatrix[i][1], C2[1], inverseMatrix[i][2], C2[2]);
			
			Te[i] = B1[i];
			Te[i + 3] = B2[i];
		}
		
		System.out.println("\nРезультат дешифрования:");
		for(int i = 0; i < 6; i++) {
			System.out.print(Te[i] + " ");
		}
		
		System.out.print("-> ");
		for (int i = 0; i < Te.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if (Te[i] == j) {
					System.out.print(alphabet[j]);
				}
			}
		}
	}
}
