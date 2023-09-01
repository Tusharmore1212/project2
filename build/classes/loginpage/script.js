/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
const startButton = document.querySelector("#click_to_convert");
const stopButton = document.querySelector("#pause_button");
const speechText = document.querySelector("#convert_text");
startButton.addEventListener("click", startRecording);
stopButton.addEventListener("click", stopRecording);

function startRecording() {
    recognition = new (window.SpeechRecognition ||
      window.webkitSpeechRecognition ||
      window.mozSpeechRecognition ||
      window.msSpeechRecognition)();
    recognition.interimResults = true;
    recognition.maxAlternatives = 1;
    recognition.continuous = true;
   
    recognition.start();
  
    startButton.disabled=true;
     stopButton.disabled=false;
    
    recognition.onresult = function(event) {
      let interimTranscript = "";
      let finalTranscript = "";
      for (let i = event.resultIndex; i < event.results.length; i++) {
        if (event.results[i].isFinal) {
          finalTranscript += event.results[i][0].transcript;
        } else {
          interimTranscript += +" "+event.results[i][0].transcript + " ";
        }
      }
      //speechText.value = "";
     // output.textContent = speechText.value  + interimTranscript + " " + finalTranscript;
     
      speechText.value +=  finalTranscript;
      
    };
    
  }


  function stopRecording() {
    recognition.stop();
  }

  function stopRecording() {
    recognition.stop();
    startButton.disabled = false;
    stopButton.disabled = true;
    saveButton.disabled = false;
  }






/*

click_to_convert.addEventListener('click',function(){
    var speech = true;
    window.SpeechRecognition = window.webkitSpeechRecognition;
    const recognition = new SpeechRecognition();
    recognition.interimResults= true;
    
    recognition.addEventListener('result', e=>{
        const transcript = Array.from(e.results)
        .map(result =>result[0])
        .map(result => result.transcript)

        convert_text.innerHTML = transcript;
    })
    if(speech == true){
        recognition.start();
    }
})*/
const textarea = document.querySelector("textarea")
//const buttonsave = document.querySelector(".save_button")

save_button.addEventListener("click", () => {
    const blob = new Blob([textarea.value],{type: "text/plain"});
    const fileUrl = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.download = "output";
    link.href = fileUrl;
    link.click();
    console.log(blob)
})