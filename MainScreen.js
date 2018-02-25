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
        <View style={styles.top} >
          <Button style={{margin:5}} title=" Logon " onPress={() => { AppAuthAndroid.login() }} />    
          <Button style={{margin:5}} title=" Logoff " onPress={() => { AppAuthAndroid.logout() }} />
          <Button style={{margin:5}} title=" QR " onPress={() => { navigate('QR') }} />
          <Button style={{margin:5}} title=" Scan " onPress={() => { navigate('Scan') }} />
          <Button style={{margin:5}} title=" Image " onPress={() => { navigate('ResizeImage') }} />        
        </View>
        <View style={styles.middle} >
          <Text></Text>
        </View>
        <View style={styles.bottom} >
          <Text>Status</Text>
        </View>
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
    alignItems: 'stretch',
    justifyContent: 'center',
  },
  top: {
    flexDirection: 'row',
    justifyContent: 'center',
    padding: 5,
    backgroundColor: '#C0D0E0'
  },
  middle: {
    flex:1,
  },
  bottom: {
    backgroundColor: '#C0D0E0',
    height:20,
    paddingHorizontal: 5
  }
});
