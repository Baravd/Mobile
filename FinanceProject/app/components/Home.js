import Button from "react-native-elements/src/buttons/Button";
import * as React from "react";
import {View} from "react-native";

export default class Home extends React.Component {
    constructor(props){
        super();
       // this.auth = global.firebaseApp.auth();
        //this.currentUser =this.auth.getUser();
       // console.log(this.currentUser);
    }
    render() {
        const {navigate} = this.props.navigation;
        return <View>
            <Button
                title={"Show all expenses"}
                onPress={() => navigate('List')}
            />
            <Button
                title={"Statistics" }
                onPress={() => navigate('Statistics')}
            />
            <Button
                title={"Email Us"}
                onPress={()=>navigate('Mail')}
            />
            /*<Button title="LOG OUT" onPress={() => {
                this.auth.signOut();
                this.props.navigation.goBack();
            }}/>*/
        </View>
    }

}