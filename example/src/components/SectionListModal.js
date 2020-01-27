import React from 'react';
import {
    Text,
    View,
    TouchableOpacity,
    SectionList,
} from 'react-native';
import StyledModal from "./StyledModal";

/*
  type Props = {
    onSelectItem: (item) => void,
    data = [
        {
            title: string,
            data: [ { id: string, title: string } ]
        }
    ]
  }
 */
const SectionListModal = props => {
    const { data, onSelectItem, ...modalProps } = props;

    return (
        <StyledModal { ...modalProps }>
            <SectionList
                sections={data}
                keyExtractor={(item, index) => item + index}
                style={{
                    borderBottomWidth: 1,
                    borderBottomColor: '#ccc',
                    borderTopWidth: 1,
                    borderTopColor: '#ccc',
                }}
                ItemSeparatorComponent={() => (
                    <View style={{height: 1, backgroundColor: '#ccc'}} />
                )}
                renderSectionHeader={({ section: { title } }) => (
                    <Text style={{fontSize: 18, fontWeight: "bold", color: "#fff", backgroundColor: "#333", paddingHorizontal: 10}}>{title}</Text>
                )}
                renderItem={({item}) => (
                    <TouchableOpacity
                        style={{
                            height: 50,
                            flex: 1,
                            padding: 10,
                            justifyContent: 'center',
                        }}
                        onPress={() => onSelectItem(item)}>
                        <Text>{item.title}</Text>
                    </TouchableOpacity>
                )}
            />
        </StyledModal>
    );
};

export default SectionListModal;
