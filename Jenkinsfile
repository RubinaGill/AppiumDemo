{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf830
{\fonttbl\f0\fnil\fcharset0 Menlo-Regular;}
{\colortbl;\red255\green255\blue255;\red31\green32\blue33;\red249\green249\blue249;\red98\green9\blue1;
\red251\green0\blue7;\red210\green9\blue5;}
{\*\expandedcolortbl;;\cssrgb\c16078\c16863\c17255;\cssrgb\c98039\c98039\c98039;\cssrgb\c46667\c6667\c0;
\cssrgb\c100000\c0\c0\c4706;\cssrgb\c86667\c13333\c0;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720\sl300\partightenfactor0

\f0\fs25\fsmilli12600 \cf2 \cb3 \expnd0\expndtw0\kerning0
\outl0\strokewidth0 \strokec2 pipeline \{\
    agent \{\
        docker \{\
            image \cf4 \cb5 \strokec4 '\cf6 \strokec6 maven:3-alpine\cf4 \strokec4 '\cf2 \cb3 \strokec2  \
            args \cf4 \cb5 \strokec4 '\cf6 \strokec6 -v /root/.m2:/root/.m2\cf4 \strokec4 '\cf2 \cb3 \strokec2  \
        \}\
    \}\
    stages \{\
        stage(\cf4 \cb5 \strokec4 '\cf6 \strokec6 Build\cf4 \strokec4 '\cf2 \cb3 \strokec2 ) \{ \
            steps \{\
                sh \cf4 \cb5 \strokec4 '\cf6 \strokec6 mvn -B -DskipTests clean package\cf4 \strokec4 '\cf2 \cb3 \strokec2  \
            \}\
        \}\
    \}\
\}\
}