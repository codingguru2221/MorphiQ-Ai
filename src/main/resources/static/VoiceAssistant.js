import React, { useState, useEffect, useRef } from 'react';

const VoiceAssistant = () => {
  const [isListening, setIsListening] = useState(false);
  const [isSpeaking, setIsSpeaking] = useState(false);
  const [transcript, setTranscript] = useState('');
  const [response, setResponse] = useState('');
  const [profession, setProfession] = useState('doctor');
  const [userId, setUserId] = useState('user123');
  
  const recognitionRef = useRef(null);
  const synthRef = useRef(window.speechSynthesis);

  // Initialize speech recognition
  useEffect(() => {
    if ('webkitSpeechRecognition' in window || 'SpeechRecognition' in window) {
      const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
      recognitionRef.current = new SpeechRecognition();
      recognitionRef.current.continuous = false;
      recognitionRef.current.interimResults = true;
      recognitionRef.current.lang = 'en-US';

      recognitionRef.current.onresult = (event) => {
        const transcript = Array.from(event.results)
          .map(result => result[0])
          .map(result => result.transcript)
          .join('');
        setTranscript(transcript);
      };

      recognitionRef.current.onerror = (event) => {
        console.error('Speech recognition error', event.error);
        setIsListening(false);
      };

      recognitionRef.current.onend = () => {
        setIsListening(false);
        if (transcript.trim()) {
          sendVoiceMessage(transcript);
        }
      };
    } else {
      console.warn('Speech recognition not supported in this browser.');
    }

    return () => {
      if (recognitionRef.current) {
        recognitionRef.current.stop();
      }
    };
  }, []);

  const toggleListening = () => {
    if (isListening) {
      recognitionRef.current.stop();
      setIsListening(false);
    } else {
      setTranscript('');
      setResponse('');
      recognitionRef.current.start();
      setIsListening(true);
    }
  };

  const sendVoiceMessage = async (message) => {
    try {
      const response = await fetch('/api/chat/message', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          userId: userId,
          profession: profession,
          message: message
        }),
        credentials: 'same-origin'
      });

      const data = await response.json();
      
      // Set the response text
      const fullResponse = `${data.response}`;
      setResponse(fullResponse);
      
      // Speak the response
      speakResponse(fullResponse);
    } catch (error) {
      console.error('Error:', error);
      speakResponse("Sorry, I encountered an error processing your request.");
    }
  };

  const speakResponse = (text) => {
    if (synthRef.current.speaking) {
      synthRef.current.cancel();
    }
    
    setIsSpeaking(true);
    const utterance = new SpeechSynthesisUtterance(text);
    utterance.lang = 'en-US';
    utterance.rate = 1.0;
    utterance.pitch = 1.0;
    
    utterance.onend = () => {
      setIsSpeaking(false);
    };
    
    utterance.onerror = () => {
      setIsSpeaking(false);
    };
    
    synthRef.current.speak(utterance);
  };

  return (
    <div style={styles.container}>
      <div style={styles.voiceAssistant}>
        {/* Visual indicator for listening/speaking */}
        <div style={{
          ...styles.indicator,
          backgroundColor: isListening ? '#ff3e1c' : isSpeaking ? '#1c8cff' : '#060606',
          boxShadow: isListening 
            ? '0 0 20px #ff3e1c88, 0 0 40px #ff3e1c88' 
            : isSpeaking 
            ? '0 0 20px #1c8cff88, 0 0 40px #1c8cff88' 
            : 'none',
          transform: isListening || isSpeaking ? 'scale(1.1)' : 'scale(1)'
        }}>
          <div style={styles.indicatorInner} />
        </div>
        
        <div style={styles.controls}>
          <button 
            onClick={toggleListening}
            style={{
              ...styles.button,
              backgroundColor: isListening ? '#ff3e1c' : '#007bff'
            }}
          >
            {isListening ? 'Stop Listening' : 'Start Voice Command'}
          </button>
          
          <div style={styles.status}>
            {isListening && <p style={styles.listening}>Listening...</p>}
            {isSpeaking && <p style={styles.speaking}>Speaking...</p>}
          </div>
        </div>
        
        {transcript && (
          <div style={styles.transcript}>
            <h3>You said:</h3>
            <p>{transcript}</p>
          </div>
        )}
        
        {response && (
          <div style={styles.response}>
            <h3>AI Response:</h3>
            <p>{response}</p>
          </div>
        )}
      </div>
    </div>
  );
};

// Styles
const styles = {
  container: {
    fontFamily: 'Arial, sans-serif',
    maxWidth: '600px',
    margin: '20px auto',
    padding: '20px'
  },
  voiceAssistant: {
    textAlign: 'center'
  },
  indicator: {
    width: '200px',
    height: '200px',
    borderRadius: '50%',
    margin: '0 auto 20px',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    transition: 'all 0.3s ease',
    position: 'relative',
    overflow: 'hidden'
  },
  indicatorInner: {
    width: '160px',
    height: '160px',
    borderRadius: '50%',
    background: 'rgba(255, 255, 255, 0.1)',
    backdropFilter: 'blur(10px)'
  },
  controls: {
    marginBottom: '20px'
  },
  button: {
    padding: '12px 24px',
    fontSize: '16px',
    color: 'white',
    border: 'none',
    borderRadius: '30px',
    cursor: 'pointer',
    transition: 'all 0.3s ease',
    boxShadow: '0 4px 15px rgba(0, 0, 0, 0.2)'
  },
  status: {
    marginTop: '10px'
  },
  listening: {
    color: '#ff3e1c',
    fontWeight: 'bold'
  },
  speaking: {
    color: '#1c8cff',
    fontWeight: 'bold'
  },
  transcript: {
    backgroundColor: '#f0f8ff',
    padding: '15px',
    borderRadius: '10px',
    margin: '15px 0'
  },
  response: {
    backgroundColor: '#f5f5f5',
    padding: '15px',
    borderRadius: '10px',
    margin: '15px 0'
  }
};

export default VoiceAssistant;