import {Linking} from "react-navigation";
import {TextInput, View} from "react-native";
import * as React from "react";
import Button from "react-native-elements/src/buttons/Button";
import Communications from 'react-native-communications';


export class Mail extends React.Component{
    constructor(props){
        super(props);
        this.state={message: ''}


    }
    render() {
        //let openURL = Linking.openURL('mailto:somethingemail@gmail.com?subject=abcdefg&body=body');
        return <View>
            <TextInput
                placeholder={"Message"}
                onChangeText={(message) => this.setState({message:message})}


            />
            <Button
                title={"Send"}
                onPress={()=>{
                    Communications.email(['example@gmail.com'],null , null, 'Bug Report', this.state.message);
                }}
            />

        </View>
    }
}