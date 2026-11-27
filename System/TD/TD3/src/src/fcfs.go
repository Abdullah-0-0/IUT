package main

var fileAttent []task = make([]task,0)
var cursor *task
// Ordonnanceur premier arrivé premier servi (first come first served)
func fcfs(newTasks []task) (currentTask *task) {

    fileAttent = append(fileAttent,newTasks...)

    if len(fileAttent) == 0{
        return nil
    }
    cursor = &fileAttent[0]

    fileAttent = fileAttent[1:]
	return cursor
}

