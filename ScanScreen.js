import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import QRCodeScanner from 'react-native-qrcode-scanner';

export default class ScanScreen extends React.Component {
  static navigationOptions = { title: 'Scan' };
  
  onRead(event) {
    const { goBack } = this.props.navigation;       
    console.log('OnRead: ', event.data);
    goBack();
  }

  render() {
    const { navigate } = this.props.navigation;    
    return (
      <View style={styles.container}>
        <QRCodeScanner 
          showMarker={ true }
          onRead={ (event) => { this.onRead(event) }} 
          topContent={ <Text> Top Content </Text> }
          bottomContent={ <Text> Bottom Content </Text> }
        />
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

