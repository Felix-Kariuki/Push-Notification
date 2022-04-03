const functions = require("firebase-functions");
const admin = require('firebase-admin');
admin.initializeApp();

exports.databaseDrivenPushNotifs = functions.database.ref('/donation/{donationId}').onCreate(
    (snapshot, context) => {
        admin.database().ref('/device_token').once('value').then(allTokens =>{
            if(allTokens.val()){
                console.log("Token available");
                const token = Object.keys(allTokens.val());
                console.log(token)
                admin.messaging().sendToDevice(token,
                    {
                        notification: {
                            title: "Donation Received",
                            body: "Dear, your donation has been received, kindly wait for your balance to update"
                        }
                    }
                );
            }else{
                console.log("Token not available")
            }
        });
    }
);