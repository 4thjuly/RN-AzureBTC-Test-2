import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import AppAuthAndroid from './AppAuthAndroid';
import QRCode from 'react-native-qrcode';

export default class ScanResultScreen extends React.Component {
  static navigationOptions = { title: 'Scan Result' };
  
  render() {
    const text = this.props.navigation.state.params.text;
    return (
      <View style={styles.container}>
        <Text style={{margin:5}}> {text} </Text>
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
