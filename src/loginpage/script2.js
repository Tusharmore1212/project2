// create an instance of the SpeechRecognition object
const recognition = new webkitSpeechRecognition();
recognition.continuous = true;
recognition.interimResults = true;
recognition.lang = 'en-US';

// initialize the output variable
let outputText = '';

// add an event listener to the file input element
const fileInput = document.getElementById('fileInput');
fileInput.addEventListener('change', function() {
  // create an instance of the FileReader object
  const reader = new FileReader();

  // add an event listener to the reader object
  reader.addEventListener('load', function() {
    // create an instance of the Audio object
    const audio = new Audio(reader.result);

    // play the audio and start the speech recognition
    audio.play();
    recognition.start();
  });

  // read the selected file as a data URL
  reader.readAsDataURL(fileInput.files[0]);
});



// add an event listener to the recognition object
 recognition.addEventListener('result', e=>{
        const transcript = Array.from(e.results)
        .map(result =>result[0])
        .map(result => result.transcript)

        outputText = transcript;
    
  // display the output variable in the output element
  const output = document.getElementById('output');
  output.value = outputText;
});

// add an event listener to the save button
const saveButton = document.getElementById('saveButton');
saveButton.addEventListener('click', function() {
  // create a new Blob object containing the output content
  const blob = new Blob([outputText], { type: 'text/plain' });

  // create a new anchor element
  const anchor = document.createElement('a');
  anchor.href = URL.createObjectURL(blob);
  anchor.download = 'output.txt';

  // simulate a click on the anchor element
  anchor.click();
});
