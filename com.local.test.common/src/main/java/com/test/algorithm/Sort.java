package com.test.algorithm;

public class Sort {
	/**
	 * 冒泡排序 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
	 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。 针对所有的元素重复以上的步骤，除了最后一个。
	 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
	 * 
	 * @param numbers
	 *            需要排序的整型数组
	 */
	public static void bubbleSort(int[] numbers) {
		int temp = 0;
		int size = numbers.length;
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				if (numbers[j] > numbers[j + 1]) // 交换两数位置
				{
					temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
	 * 
	 * @param numbers
	 *            带查找数组
	 * @param low
	 *            开始位置
	 * @param high
	 *            结束位置
	 * @return 中轴所在位置
	 */
	public static int getMiddle(int[] numbers, int low, int high) {
		int temp = numbers[low]; // 数组的第一个作为中轴
		while (low < high) {
			while (low < high && numbers[high] > temp) {
				high--;
			}
			numbers[low] = numbers[high];// 比中轴小的记录移到低端
			while (low < high && numbers[low] < temp) {
				low++;
			}
			numbers[high] = numbers[low]; // 比中轴大的记录移到高端
		}
		numbers[low] = temp; // 中轴记录到尾
		return low; // 返回中轴的位置
	}

	/**
	 * 
	 * @param numbers
	 *            带排序数组
	 * @param low
	 *            开始位置
	 * @param high
	 *            结束位置
	 */
	public static void quickSort(int[] numbers, int low, int high) {
		if (low < high) {
			int middle = getMiddle(numbers, low, high); // 将numbers数组进行一分为二
			quickSort(numbers, low, middle - 1); // 对低字段表进行递归排序
			quickSort(numbers, middle + 1, high); // 对高字段表进行递归排序
		}

	}

	/**
	 * 选择排序算法 在未排序序列中找到最小元素，存放到排序序列的起始位置 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
	 * 以此类推，直到所有元素均排序完毕。
	 * 
	 * @param numbers
	 */
	public static void selectSort(int[] numbers) {
		int size = numbers.length; // 数组长度
		int temp = 0; // 中间变量

		for (int i = 0; i < size; i++) {
			int k = i; // 待确定的位置
			// 选择出应该在第i个位置的数
			for (int j = size - 1; j > i; j--) {
				if (numbers[j] < numbers[k]) {
					k = j;
				}
			}
			// 交换两个数
			temp = numbers[i];
			numbers[i] = numbers[k];
			numbers[k] = temp;
		}
	}

	/**
	 * 插入排序
	 * 
	 * 从第一个元素开始，该元素可以认为已经被排序 取出下一个元素，在已经排序的元素序列中从后向前扫描
	 * 如果该元素（已排序）大于新元素，将该元素移到下一位置 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置 将新元素插入到该位置中 重复步骤2
	 * 
	 * @param numbers
	 *            待排序数组
	 */
	public static void insertSort(int[] numbers) {
		int size = numbers.length;
		int temp = 0;
		int j = 0;

		for (int i = 0; i < size; i++) {
			temp = numbers[i];
			// 假如temp比前面的值小，则将前面的值后移
			for (j = i; j > 0 && temp < numbers[j - 1]; j--) {
				numbers[j] = numbers[j - 1];
			}
			numbers[j] = temp;
		}
	}

	public static void main(String[] args) {

		int[] numbers = { 3, 5, 7, 98, 1, 2, 4, 6, 9, 5, 4, 4, 78, 89, 345 };

		// bubbleSort(numbers);

		// quickSort(numbers, 0, numbers.length-1);

//		selectSort(numbers);
		
		insertSort(numbers);

		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + ",");
		}
	}

}
