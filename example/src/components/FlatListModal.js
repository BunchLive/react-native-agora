import React from 'react';
import {
    Text,
    View,
    FlatList,
    TouchableOpacity,
} from 'react-native';
import StyledModal from "./StyledModal";

/*
  type Props = {
    onSelectItem: (item) => void,
     data: [
        { id: string, title: string }
     ]
  }
 */
const FlatListModal = props => {
    const { data, onSelectItem, ...modalProps } = props;

    return (
        <StyledModal { ...modalProps }>
            <FlatList
                data={data}
                style={{
                    borderBottomWidth: 1,
                    borderBottomColor: '#ccc',
                    borderTopWidth: 1,
                    borderTopColor: '#ccc',
                }}
                ItemSeparatorComponent={() => (
                    <View style={{height: 1, backgroundColor: '#ccc'}} />
                )}
                renderItem={({item}) => (
                    <TouchableOpacity
                        style={{
                            height: 60,
                            flex: 1,
                            padding: 10,
                            justifyContent: 'center',
                        }}
                        onPress={() => onSelectItem(item)}>
                        <Text>{item.title}</Text>
                    </TouchableOpacity>
                )}
                keyExtractor={item => item.id}
            />
        </StyledModal>
    );
};

export default FlatListModal;
