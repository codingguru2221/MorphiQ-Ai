// Test script to verify voice assistant functionality
async function testVoiceAssistant() {
    try {
        // Test the chat API endpoint directly
        const response = await fetch('http://localhost:8080/api/chat/message', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: 'testUser',
                profession: 'doctor',
                message: 'Hello, what should I do for a patient with fever?'
            })
        });
        
        const data = await response.json();
        console.log('API Response:', data);
        
        if (data.response) {
            console.log('SUCCESS: Voice assistant is working correctly');
            console.log('Response:', data.response);
        } else {
            console.log('ERROR: No response from voice assistant');
        }
    } catch (error) {
        console.log('ERROR: Failed to communicate with voice assistant');
        console.log('Error details:', error);
    }
}

// Run the test
testVoiceAssistant();