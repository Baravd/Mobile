import Button from "react-native-elements/src/buttons/Button";
import * as React from "react";
import {View} from "react-native";

export default class Home extends React.Component {
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
        </View>
    }

}