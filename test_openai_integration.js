// Test script to verify OpenAI integration
async function testOpenAIIntegration() {
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
                message: 'What are the symptoms of diabetes?'
            })
        });
        
        const data = await response.json();
        console.log('API Response:', data);
        
        if (data.response) {
            console.log('SUCCESS: OpenAI integration is working correctly');
            console.log('Response:', data.response);
        } else {
            console.log('ERROR: No response from OpenAI integration');
        }
    } catch (error) {
        console.log('ERROR: Failed to communicate with the API');
        console.log('Error details:', error);
    }
}

// Run the test
testOpenAIIntegration();