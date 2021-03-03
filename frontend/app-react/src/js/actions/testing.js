
import axios from "axios";


export function testing() {

    return function(dispatch)  {
        let url =  'http://localhost:3000/about'
        axios.get(url)
            .then((response) => {
                localStorage.removeItem( "token" )
                localStorage.setItem( "token", response.data.token );
                dispatch({type: "SUCCESS", payload: response.data})
            })
            .catch((err) => {
                dispatch({type: "FAILED", payload: err})
            })
    }
}