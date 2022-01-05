/**
 * 
 */

//1부터 10까지의 합
function proc1(){
  let sum = 0;
  for(i=0; i<=10; i++){
	sum += i;
  }
  document.getElementById('d1').textContent = sum;
}

//1부터 200까지 짝수의 합
proc2=function(){
  let sum = 0;
  //방식1
  /*
  for(i=2; i<=200; i+=2){
	sum += i;
  }
  */
  //방식2
  /*
  for(i=1; i<=200; i++){
	if(i%2==0) sum += i;
  }
  */
  //방식3
  for(i=1; i<=200; i++){
	if(i%2!=0) continue;
	sum += i;
  }
  document.getElementById('d2').textContent = sum;
}