//-----------------------------------------------------------------------------
// ex4.c
// illustrates memory leaks
// compile: gcc ex4.c
// check for leaks: valgrind --leak-check=full a.out 
//-----------------------------------------------------------------------------

#include<stdlib.h>
int main(void){
   int* p;
   p = malloc(sizeof(int)); // this memory is leaked
   *p = 6;
   p = malloc(sizeof(int)); // this memory is not leaked
   *p = 7;
   free(p); // here is where the second malloc is freed
   return 0;
}




