import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import AppAuthAndroid from './AppAuthAndroid';

export default class App extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Text>Sample app with native module - v0.03</Text>
        <Button title=" Logon " onPress={ () => { AppAuthAndroid.configureService() }} /> 
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
