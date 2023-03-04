const http = require('http');

// Create the HTTP server
const server = http.createServer((req, res) => {
  // Check if the incoming request is a GET request
  if (req.method === 'GET') {
    // Log a message to the console
    console.log('Received a GET request!');
  }

  // Send a response back to the client
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end('Hello, world!');
});

// Start the server and listen for incoming requests
server.listen(3000, () => {
  console.log('Server listening on port 3000');
});

//192.168.1.103
