import React from 'react';
import {StyleSheet, Text, View, TextInput, Button, Linking, Alert} from 'react-native';

class Anchor extends React.Component {
    _handlePress = () => {

        this.props.onPress && this.props.onPress();
    };

    render() {
        return (
            <Button title={this.props.title} onPress={this._handlePress}/>
        );
    }
}

export class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {username: "", password: ""};
        this.auth = global.firebaseApp.auth()
        const {navigate} = this.props.navigation;
        console.log("propos=" + JSON.stringify(this.props));
        console.log("navigate=" + JSON.stringify(navigate));
        //this.props.navigation.navigate('Home');//, {uid: user.uid});s

        /*this.auth.onAuthStateChanged((user) => {

            if (user) {
                console.log("Navigate obj=" + JSON.stringify(navigate) + " my props=" + JSON.stringify(this.props));
                console.log("Autentificarea userului=" + user.email + " user id=" + user.uid);
            }
            else {
                console.log("Eroare");
                if (this.state.username != "" && this.state.password != "") {
                    Alert.alert('Log in failed', 'Wrong username or password', [{
                        text: 'OK',
                        onPress: () => console.log('OK')
                    }]);
                }
            }
        })*/
    }

    render() {
        return (
            <View style={styles.container}>
                <View style={{flexDirection: 'column', flex: 0.2, width: '80%'}}>
                    <TextInput style={{flex: 0.8, height: 40}} onChangeText={(username) => this.setState({username})}/>
                    <TextInput secureTextEntry={true} style={{flex: 0.8, height: 40}}
                               onChangeText={(password) => this.setState({password})}/>
                    <Button title="LOGIN" onPress={() => {
                        this.auth.signInWithEmailAndPassword(this.state.username, this.state.password).catch(function (error) {
                            // Handle Errors here.
                            const errorCode = error.code;
                            const errorMessage = error.message;
                            console.log(errorCode + ' ' + errorMessage);
                            // ...
                        });//.then(this.props.navigation.navigate('Home')
                    }}/>
                    <Button title="REGISTER" onPress={() => {
                        this.props.navigation.navigate('Register');
                    }
                    }
                    />
                </View>
            </View>
        );
    }
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
});
