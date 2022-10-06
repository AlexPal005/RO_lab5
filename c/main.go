package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

type Barrier struct {
	c      int
	n      int
	m      sync.Mutex
	before chan int
	after  chan int
}

func New(n int) *Barrier {
	b := Barrier{
		n:      n,
		before: make(chan int, 1),
		after:  make(chan int, 1),
	}
	b.after <- 1
	return &b
}
func (b *Barrier) Before() {
	b.m.Lock()
	b.c += 1
	if b.c == b.n {
		<-b.after
		b.before <- 1
	}
	b.m.Unlock()
	<-b.before
	b.before <- 1
}
func (b *Barrier) After() {
	b.m.Lock()
	b.c -= 1
	if b.c == 0 {
		<-b.before
		b.after <- 1
	}
	b.m.Unlock()
	<-b.after
	b.after <- 1
}

func main() {
	var wg sync.WaitGroup
	wg.Add(3)
	count_threads := 3
	br := New(count_threads)

	var arr []int
	chanel := make(chan int)
	/////////////////////////////////////////////////////////////////
	arr1 := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 10}
	go counter(arr1, br, chanel, &arr, &wg)
	/////////////////////////////////////////////////////////////////
	arr2 := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 10}
	go counter(arr2, br, chanel, &arr, &wg)
	/////////////////////////////////////////////////////////////////
	arr3 := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 11}
	go counter(arr3, br, chanel, &arr, &wg)

	go add_value(&arr, chanel)
	wg.Wait()
}

func counter(arr []int, br *Barrier, chanel chan int, array *[]int, wg *sync.WaitGroup) {
	for {
		time.Sleep(1 * time.Second)
		sum := 0
		for i := 0; i < len(arr); i++ {
			sum += arr[i]
		}
		chanel <- sum
		fmt.Println("Сума", sum)

		br.Before()

		if is_equal(array) {
			break
		}

		br.After()

		time.Sleep(1 * time.Second)
		rand.Seed(time.Now().UnixNano())
		rand_val := rand.Intn(4)
		fmt.Println("Рандомне число: ", rand_val)
		if rand_val == 0 || rand_val == 2 {
			arr[0] = arr[0] + 1
		} else {
			arr[0] = arr[0] - 1
		}
		clear(array)

	}
	wg.Done()
}
func add_value(array *[]int, chanel chan int) {
	for {
		sum := <-chanel
		*array = append(*array, sum)
		if len(*array) == 3 {
			if is_equal(array) {
				break
			}
		}
	}
}
func is_equal(array *[]int) bool {
	if ((*array)[0] == (*array)[1]) && ((*array)[1] == (*array)[2]) {
		fmt.Println("Суми рівні")
		return true
	}
	return false
}
func clear(array *[]int) {
	var new_arr []int
	*array = new_arr
}
