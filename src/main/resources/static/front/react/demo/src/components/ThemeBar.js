import React from 'react'
import ThemContext from "../theme-context";

const ThemeBar = () => {
    return (
        <ThemContext.Consumer>
            {
                theme => {
                    console.log(theme)
                    return (
                        <div
                            className="alert mt-5"
                            style={{backgroundColor: theme.bgColor, color: theme.color}}>
                            样式区域
                            <button className={theme.classnames}>
                                样式按钮
                            </button>
                        </div>
                    )
                }
            }
        </ThemContext.Consumer>
    )
}

export default ThemeBar