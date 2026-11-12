package main

import "fmt"

func routine1(c chan bool) {
	for {
		b := <-c
		if !b {
			fmt.Println("Routine 1")
			b = !b
		}
		c <- b
	}

}
func routine2(c chan bool) {
	for {
		b := <-c
		if !b {
			fmt.Println("Routine 2")
			b = !b
		}
		c <- b
	}
}

func main() {
	var c chan bool = make(chan bool)
	go routine1(c)
	routine2(c)
}
