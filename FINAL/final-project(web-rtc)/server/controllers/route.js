const fs = require('fs');

module.exports = (app) =>{
    app.get('/', (req, res) => {
        res.render('index.ejs', {
            title : 'web-rtc',
            url : ''
        });
    }).get('/makeRoom', (req, res) => {
        res.render('makeRoom/makeRoom.html', {
            title : 'makeRoom'
        });
    }).post('/',(req, res) => {
        res.render('index.ejs', {
            url : req.body.inputUrl
        });
    }).post('/meetingRoom/', (req, res) => {
        res.render('meetingRoom/meetingRoom.ejs', {
            nickname : req.body.nickname,
            room_addr : req.body.room_addr,
            room_maxval : req.body.room_maxval
        });
        
    }).get('/joinRoom/', (req, res) => {
        res.render('meetingRoom/joinRoom.ejs');
        });

    
}