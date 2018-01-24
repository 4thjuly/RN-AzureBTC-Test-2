import React from 'react';
import { StyleSheet, Text, View, Button, Platform } from 'react-native';
import AppAuthAndroid from './AppAuthAndroid';
import AppAuthIos from './AppAuthIos';
import QRCode from 'react-native-qrcode';

export default class MainScreen extends React.Component {
  static navigationOptions = { title: 'Main' };
  
  render() {
    const { navigate } = this.props.navigation;    
    return (
      <View style={styles.container}>
        <Text style={{margin:5}}>Sample app with native module - v0.05</Text>
        <View style={{flexDirection:'row', margin:5}}>
          <Button style={{margin:5}} title=" Logon " onPress={ () => { this.login() }} /> 
          <Button style={{margin:5}} title=" Logoff " onPress={ () => { this.logout() }} />
          <Button style={{margin:5}} title=" QR " onPress={ () => { navigate('QR')}} />
          <Button style={{margin:5}} title=" Scan " onPress={ () => { navigate('Scan')}} />
        </View>
        {/* <QRCode value='This is a test' size={200} bgColor='darkblue' fgColor='lightblue'/> */}
      </View>
    );
  }

  login() {
    if (Platform.OS === 'ios') {
      // AppAuthIos.test('This is a test');
      AppAuthIos.login();
    } else {
      AppAuthAndroid.login();
    }

  }

  logout() {
    if (Platform.OS === 'ios') {
      AppAuthIos.test('This is a test');
    } else {
      AppAuthAndroid.logout();
    }
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
